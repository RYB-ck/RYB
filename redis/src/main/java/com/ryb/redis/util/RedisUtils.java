package com.ryb.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author 常坤
 */
@Component
public class RedisUtils {
    final RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean getAndSet(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(final String key) {
        boolean result;
        try {
            result = redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean delete(Collection<Object> keys) {
        try {
            Long result = redisTemplate.delete(keys);
            if (result != null && keys.size() == result.intValue()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
