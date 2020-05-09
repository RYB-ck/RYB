package com.ryb.oauth.controller;

import com.ryb.core.po.User;
import com.ryb.core.result.Result;
import com.ryb.oauth.service.impl.UserAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 常坤
 */
@RestController
@RequestMapping("/Oauth")
public class OauthController {
    final UserAuthServiceImpl userAuthService;

    @Autowired
    public OauthController(UserAuthServiceImpl userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping(value = "authUser", produces = {"application/json;charset=UTF-8"})
    public Result<?> authUser(@RequestBody User user) {
        return userAuthService.authUser(user);
    }

    @PostMapping(value = "register", produces = {"application/json;charset=UTF-8"})
    public Result<?> register(@RequestBody User user) {
        return userAuthService.register(user);
    }
}
