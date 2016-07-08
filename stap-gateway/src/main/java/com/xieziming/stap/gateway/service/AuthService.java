package com.xieziming.stap.gateway.service;

import com.xieziming.stap.gateway.mode.AuthResult;

/**
 * Created by Suny on 7/6/16.
 */
public interface AuthService {
    AuthResult auth(String principal, String password);
}
