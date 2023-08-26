package com.groupfour.eMovie.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdGenerator {

    // 开始时间戳
    private static final long BEGIN_TIMESTAMP = 1672531200L;
    // 序列号位数
    private static final int COUNT_BITS = 32;

    private StringRedisTemplate stringRedisTemplate;

    public RedisIdGenerator(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /*
    *   @param keyPrefix 用于区分业务的前缀
    *
    * */
    public long nextId(String keyPrefix) {
        // 1 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;
        // 2 生成序列号
        // 2.1 获取当前日期，精确到天；这样生成序列号，避免订单数超过2^32上限，因为每天不可能有这么多订单，同时方便统计数据
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        // 2.2 自增长，此处不会是null pointer，因为如果redis发现key为null，会自动创建一条记录并自增
        long count = stringRedisTemplate.opsForValue().increment("icr:" + keyPrefix + ":" + date);
        // 3 拼接并返回
        return timestamp << COUNT_BITS | count;
    }

//    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
//        long second = localDateTime.toEpochSecond(ZoneOffset.UTC);
//        System.out.println(second);
//    }
}
