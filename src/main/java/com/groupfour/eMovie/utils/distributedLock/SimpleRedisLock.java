package com.groupfour.eMovie.utils.distributedLock;

import cn.hutool.core.lang.UUID;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

import static com.groupfour.eMovie.utils.ProjectConstants.REDIS_DISTRIBUTED_LOCK_KEY_PREFIX;

public class SimpleRedisLock implements ILock{

    private StringRedisTemplate stringRedisTemplate;

    private String name;

    private static final String ID_PREFIX = UUID.randomUUID().toString(true) + "-";

    public SimpleRedisLock(StringRedisTemplate stringRedisTemplate, String name) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.name = name;
    }

    @Override
    public boolean tryLock(long timeoutSec) {
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        Boolean success = stringRedisTemplate.opsForValue().
                setIfAbsent(REDIS_DISTRIBUTED_LOCK_KEY_PREFIX + name, threadId, timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    @Override
    public void unlock() {
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        String value = stringRedisTemplate.opsForValue().get(REDIS_DISTRIBUTED_LOCK_KEY_PREFIX + name);
        if (threadId.equals(value)) {
            stringRedisTemplate.delete(REDIS_DISTRIBUTED_LOCK_KEY_PREFIX + name);
        }
    }
}
