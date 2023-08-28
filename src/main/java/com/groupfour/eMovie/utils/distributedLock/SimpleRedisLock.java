package com.groupfour.eMovie.utils.distributedLock;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

import static com.groupfour.eMovie.utils.ProjectConstants.REDIS_DISTRIBUTED_LOCK_KEY_PREFIX;

public class SimpleRedisLock implements ILock{

    private StringRedisTemplate stringRedisTemplate;

    private String name;

    public SimpleRedisLock(StringRedisTemplate stringRedisTemplate, String name) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.name = name;
    }

    @Override
    public boolean tryLock(long timeoutSec) {
        long threadId = Thread.currentThread().getId();
        Boolean success = stringRedisTemplate.opsForValue().
                setIfAbsent(REDIS_DISTRIBUTED_LOCK_KEY_PREFIX + name, threadId + "", timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    @Override
    public void unlock() {
        stringRedisTemplate.delete(REDIS_DISTRIBUTED_LOCK_KEY_PREFIX + name);
    }
}
