package com.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisQueueService {

    private static final String QUEUE_KEY = "my-redis-queue";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void addToQueue(Object message) {
        redisTemplate.opsForList().leftPush(QUEUE_KEY, message);
    }

    public Object popFromQueue() {
        return redisTemplate.opsForList().rightPop(QUEUE_KEY);
    }

    public long queueSize() {
        return redisTemplate.opsForList().size(QUEUE_KEY);
    }

    public void clearQueue() {
        redisTemplate.delete(QUEUE_KEY);
    }

}
