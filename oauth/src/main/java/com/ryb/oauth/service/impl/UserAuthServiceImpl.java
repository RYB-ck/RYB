package com.ryb.oauth.service.impl;

import com.ryb.core.po.User;
import com.ryb.core.result.Result;
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
    public Result<?> authUser(User user) {
        user.setUserPass(passSaltAddition.passSaltAddition(user.getUserPass()));
        if (userMapper.authUser(user)!= null) {
            return Result.success(null);
        }
        return Result.error(null);
    }

    @Override
    public Result<?> register(User user){
        user.setUserPass(passSaltAddition.passSaltAddition(user.getUserPass()));
        if (userMapper.register(user) > 0) {
            return Result.success(null);
        }
        return Result.error(null);
    }
}
