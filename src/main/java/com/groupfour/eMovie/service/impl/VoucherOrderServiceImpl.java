package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.VoucherDao;
import com.groupfour.eMovie.dao.VoucherOrderDao;
import com.groupfour.eMovie.entity.FlashVoucher;
import com.groupfour.eMovie.entity.VoucherOrder;
import com.groupfour.eMovie.service.VoucherOrderService;
import com.groupfour.eMovie.utils.RedisIdGenerator;
import com.groupfour.eMovie.utils.distributedLock.SimpleRedisLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.groupfour.eMovie.utils.ProjectConstants.REDIS_DISTRIBUTED_LOCK_KEY_PREFIX;

@Service("VoucherOrderServiceImpl")
public class VoucherOrderServiceImpl implements VoucherOrderService {

    @Autowired
    private VoucherOrderDao voucherOrderDao;

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private RedisIdGenerator redisIdGenerator;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    @Transactional
    public VoucherOrder addFlashVoucherOrder(Long voucherId, Integer uid) throws InterruptedException {
        // 1. 查询优惠券信息
        FlashVoucher flashVoucher = voucherDao.getFlashVoucherById(voucherId);
        // 2. 判断秒杀是否开始
        // 3. 判断秒杀是否结束
        LocalDateTime now = LocalDateTime.now();
        if (flashVoucher.getBegin_time().isAfter(now) || flashVoucher.getEnd_time().isBefore(now)) {
            // 尚未开始 or 已经结束
            return null;
        }

        // 4. 判断库存是否充足
        if (flashVoucher.getStock() < 1) {
            return null;
        }

        // 只适合单体模式
//        synchronized (uid.toString().intern()) {
//            // 获取当前类的代理对象
//            VoucherOrderService proxy = (VoucherOrderService) AopContext.currentProxy();
//            // 7. 返回订单
//            return proxy.createVoucherOrder(voucherId, uid);
//        }

        // 分布式锁
        // (1) 使用自己设计的锁
//        // 创建锁对象
//        SimpleRedisLock lock = new SimpleRedisLock(stringRedisTemplate, "order" + uid);
//        // 获取锁
//        boolean isLock = lock.tryLock(500);

        // (2) 使用Redisson
        RLock lock = redissonClient.getLock(REDIS_DISTRIBUTED_LOCK_KEY_PREFIX + "order" + uid);
        boolean isLock = lock.tryLock(5L, 10L, TimeUnit.SECONDS);
        // 判断是否成功获得锁
        if (!isLock) {
            // 获取失败
            return null;
        }
        try {
            // 获取当前类的代理对象
            VoucherOrderService proxy = (VoucherOrderService) AopContext.currentProxy();
            // 7. 返回订单
            return proxy.createVoucherOrder(voucherId, uid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最后释放锁
            lock.unlock();
        }
        return null;
    }

    @Transactional
    public VoucherOrder createVoucherOrder(Long voucherId, Integer uid) {
        // 查看当前用户是否已经下单
        int count = voucherOrderDao.getCountByUidAndVoucherId(uid, voucherId);
        if (count > 0) {
            // 用户已下单
            return null;
        }

        // 5. 扣减库存
        boolean success = voucherDao.updateStock(voucherId, -1);
        if (!success) {
            // 库存不足
            return null;
        }
        // 6. 创建订单
        VoucherOrder voucherOrder = new VoucherOrder();
        long orderId = redisIdGenerator.nextId("order:");
        voucherOrder.setId(orderId);
        voucherOrder.setUser_id(uid);
        voucherOrder.setVoucher_id(voucherId);
        voucherOrderDao.addFlashVoucherOrder(voucherOrder);

        return voucherOrder;
    }
}
