package com.xieziming.stap.gateway.service;

import com.xieziming.stap.gateway.mode.LoginResult;

/**
 * Created by Suny on 7/6/16.
 */
public interface LoginService {
    LoginResult login(String principal, String password);
}
