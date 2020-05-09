package com.ryb.oauth.service;

import com.ryb.core.po.User;
import com.ryb.core.result.APIResult;
import com.ryb.core.result.ResultSupport;

/**
 * @author 常坤
 */
public interface UserAuthService {
    APIResult<?> authUser(User user);

    APIResult<?> register(User user);
}
