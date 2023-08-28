-- -- 锁的key
-- local key = KEYS[1]
-- --当前线程标识
-- local threadId = ARGV[1]


-- 获取锁的线程标识
local id = redis.call('get', KEYS[1])
-- 比较threadId和id
if (ARGV[1] == id) then
    -- 释放锁
    return redis.call('del', KEYS[1])
end
return 0