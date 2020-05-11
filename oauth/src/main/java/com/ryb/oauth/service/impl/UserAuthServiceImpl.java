package com.ryb.oauth.service.impl;

import com.ryb.core.po.User;
import com.ryb.core.result.APIResult;
import com.ryb.core.resultenum.ResultEnum;
import com.ryb.core.util.InitializationToken;
import com.ryb.core.util.SnowflakeIdWorker;
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
    final InitializationToken initializationToken;

    @Autowired
    public UserAuthServiceImpl(UserMapper userMapper, PassSaltAddition passSaltAddition, RedisUtils redisUtils, InitializationToken initializationToken) {
        this.userMapper = userMapper;
        this.passSaltAddition = passSaltAddition;
        this.redisUtils = redisUtils;
        this.initializationToken = initializationToken;
    }

    @Override
    public APIResult<?> authUser(User user) {
        try {
            User authUser = userMapper.authUser(user);
            if (authUser != null) {
                if (passSaltAddition.authPass(user.getUserPass(), authUser.getUserPass())) {
                    return APIResult.newSuccessResult(initializationToken.getToken(authUser.getId()));
                }
                return APIResult.newFailResult(ResultEnum.USER_AUTH_FAIL);
            }
            return APIResult.newFailResult(ResultEnum.IS_NOT_REGISTER);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResult.newFailResult(ResultEnum.ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResult<?> register(User user) {
        try {
            if (userMapper.isRegister(user) > 0) {
                return APIResult.newFailResult(ResultEnum.IS_REGISTER);
            }
            user.setId(SnowflakeIdWorker.nextId());
            user.setUserPass(passSaltAddition.passSaltAddition(user.getUserPass()));
            if (userMapper.register(user) != null) {
                return APIResult.newSuccessResult(initializationToken.getToken(user.getId()));
            }
            return APIResult.newFailResult(ResultEnum.SAVE_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResult.newFailResult(ResultEnum.ERROR);
        }
    }
}
