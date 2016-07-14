package com.xieziming.stap.gateway.controller;

import com.google.common.cache.Cache;
import com.xieziming.stap.gateway.mode.AuthFailureReason;
import com.xieziming.stap.gateway.mode.AuthResult;
import com.xieziming.stap.gateway.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Suny on 7/6/16.
 */
@Controller
public class AuthController {
    private static Logger log = LoggerFactory.getLogger(AuthController.class);
    private final String UTF8 = ";charset=UTF-8";
    private AuthService authService;
    private Cache<String, AuthResult> userCache;

    @Autowired
    public AuthController(AuthService authService, Cache<String, AuthResult> userCache){
        this.authService = authService;
        this.userCache = userCache;
    }

    @RequestMapping(value = "authorize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public AuthResult doAuth(@RequestParam("principal") String principal, @RequestParam("password") String password){
        AuthResult authResult = authService.auth(principal.trim(), password.trim());
        if(authResult.isAuthSuccess()){
            userCache.put(principal, authResult);
        }
        return authResult;
    }

    @RequestMapping(value = "authorized", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public AuthResult isAuth(@RequestParam("principal") String principal, @RequestParam("token") String token){
        AuthResult authResult = userCache.getIfPresent(principal);
        if(token!=null && authResult.getToken().equals(token)){
            return authResult;
        }else{
            AuthResult failedAuthResult = new AuthResult();
            failedAuthResult.setAuthSuccess(false);
            failedAuthResult.setAuthFailureReason(AuthFailureReason.USER_NOT_AUTHORIZED.toString());
            return failedAuthResult;
        }
    }

    @RequestMapping(value = "invalidate", method = RequestMethod.GET)
    @ResponseBody
    public void logout(@RequestParam("principal") String principal){
        userCache.invalidate(principal);
    }
}
