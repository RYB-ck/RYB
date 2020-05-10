package com.ryb.core.util;

import com.ryb.redis.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 常坤
 */
@Component
public class InitializationToken {
    final RedisUtils redisUtils;

    @Autowired
    public InitializationToken(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    public Map<String, String> getToken(Long userId) {
        Map<String, String> result = new HashMap<>(4);
        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        redisUtils.set(accessToken, userId);
        redisUtils.set(refreshToken, userId);
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);
        return result;
    }
}
