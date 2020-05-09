package com.ryb.oauth.service.impl;

import com.ryb.core.po.User;
import com.ryb.core.result.APIResult;
import com.ryb.core.resultenum.ResultEnum;
import com.ryb.oauth.mapper.UserMapper;
import com.ryb.oauth.service.UserAuthService;
import com.ryb.oauth.util.PassSaltAddition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 常坤
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    final UserMapper userMapper;
    final PassSaltAddition passSaltAddition;

    @Autowired
    public UserAuthServiceImpl(UserMapper userMapper, PassSaltAddition passSaltAddition) {
        this.userMapper = userMapper;
        this.passSaltAddition = passSaltAddition;
    }

    @Override
    public APIResult<?> authUser(User user) {
        user.setUserPass(passSaltAddition.passSaltAddition(user.getUserPass()));
        if (userMapper.authUser(user) != null) {
            return APIResult.newSuccessResult();
        }
        return APIResult.newFailResult(ResultEnum.ERROR);
    }

    @Override
    public APIResult<?> register(User user) {
        user.setUserPass(passSaltAddition.passSaltAddition(user.getUserPass()));
        if (userMapper.register(user) != null) {
            return APIResult.newSuccessResult();
        }
        return APIResult.newFailResult(ResultEnum.ERROR);

    }
}
