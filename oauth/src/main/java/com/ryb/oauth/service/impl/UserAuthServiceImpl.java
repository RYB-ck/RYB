package com.ryb.oauth.service.impl;

import com.ryb.core.po.User;
import com.ryb.core.result.APIResult;
import com.ryb.core.resultenum.ResultEnum;
import com.ryb.oauth.mapper.UserMapper;
import com.ryb.oauth.service.UserAuthService;
import com.ryb.oauth.util.PassSaltAddition;
import com.ryb.redis.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 常坤
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    final UserMapper userMapper;
    final PassSaltAddition passSaltAddition;
    final RedisUtils redisUtils;

    @Autowired
    public UserAuthServiceImpl(UserMapper userMapper, PassSaltAddition passSaltAddition, RedisUtils redisUtils) {
        this.userMapper = userMapper;
        this.passSaltAddition = passSaltAddition;
        this.redisUtils = redisUtils;
    }

    @Override
    public APIResult<?> authUser(User user) {
        user.setUserPass(passSaltAddition.passSaltAddition(user.getUserPass()));
        System.err.println(user.getUserPass());
        if (userMapper.authUser(user) != null) {
            return APIResult.newSuccessResult("成功");
        }
        redisUtils.set("123", "123");
        return APIResult.newSuccessResult("成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult<?> register(User user) {
        user.setUserPass(passSaltAddition.passSaltAddition(user.getUserPass()));
        if (userMapper.register(user) != null) {
            return APIResult.newSuccessResult();
        }
        return APIResult.newFailResult(ResultEnum.ERROR);
    }
}
