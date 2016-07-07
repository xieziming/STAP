package com.xieziming.stap.gateway.controller;

import com.google.common.cache.Cache;
import com.xieziming.stap.gateway.mode.LoginFailureReason;
import com.xieziming.stap.gateway.mode.LoginResult;
import com.xieziming.stap.gateway.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    private final String UTF8 = ";charset=UTF-8";
    private LoginService loginService;
    private Cache<String, LoginResult> userCache;
    private String uiUrl;

    @Autowired
    public LoginController(LoginService loginService, @Value("uiUrl") String uiUrl, Cache<String, LoginResult> userCache){
        this.loginService = loginService;
        this.uiUrl = uiUrl;
        this.userCache = userCache;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public LoginResult doLogin(@RequestParam("principal") String principal, @RequestParam("password") String password){
        LoginResult loginResult = loginService.login(principal.trim(), password.trim());
        if(loginResult.isLoginSuccess()){
            userCache.put(principal, loginResult);
        }
        return loginResult;
    }

    @RequestMapping(value = "/loggedIn", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public LoginResult isLogin(@RequestParam("principal") String principal, @RequestParam("token") String token){
        LoginResult loginResult = userCache.getIfPresent(principal);
        if(token!=null && loginResult.getToken().equals(token)){
            return loginResult;
        }else{
            LoginResult failedLoginResult = new LoginResult();
            failedLoginResult.setLoginSuccess(false);
            failedLoginResult.setFailureReason(LoginFailureReason.USER_NOT_AUTHORIZED);
            return failedLoginResult;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public void logout(@RequestParam("principal") String principal){
        userCache.invalidate(principal);
    }
}
