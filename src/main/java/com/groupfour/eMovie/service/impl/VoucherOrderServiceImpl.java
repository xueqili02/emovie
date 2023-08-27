package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.VoucherDao;
import com.groupfour.eMovie.dao.VoucherOrderDao;
import com.groupfour.eMovie.entity.FlashVoucher;
import com.groupfour.eMovie.entity.VoucherOrder;
import com.groupfour.eMovie.service.VoucherOrderService;
import com.groupfour.eMovie.utils.RedisIdGenerator;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("VoucherOrderServiceImpl")
public class VoucherOrderServiceImpl implements VoucherOrderService {

    @Autowired
    private VoucherOrderDao voucherOrderDao;

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private RedisIdGenerator redisIdGenerator;

    @Override
    @Transactional
    public VoucherOrder addFlashVoucherOrder(Long voucherId, Integer uid) {
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

        synchronized (uid.toString().intern()) {
            // 获取当前类的代理对象
            VoucherOrderService proxy = (VoucherOrderService) AopContext.currentProxy();
            // 7. 返回订单
            return proxy.createVoucherOrder(voucherId, uid);
        }
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
        long orderId = redisIdGenerator.nextId("order");
        voucherOrder.setId(orderId);
        voucherOrder.setUser_id(uid);
        voucherOrder.setVoucher_id(voucherId);
        voucherOrderDao.addFlashVoucherOrder(voucherOrder);

        return voucherOrder;
    }
}
