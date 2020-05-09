package com.ryb.oauth.service;

import com.ryb.core.po.User;
import com.ryb.core.result.Result;

/**
 * @author 常坤
 */
public interface UserAuthService {
    Result<?> authUser(User user);

    Result<?> register(User user);
}
