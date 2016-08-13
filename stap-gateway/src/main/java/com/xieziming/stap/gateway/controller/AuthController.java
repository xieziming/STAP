package com.xieziming.stap.gateway.controller;

import com.xieziming.stap.core.exceptions.StapException;
import com.xieziming.stap.core.security.auth.AuthResult;
import com.xieziming.stap.core.security.auth.AuthService;
import com.xieziming.stap.core.security.auth.Credential;
import com.xieziming.stap.core.security.auth.CredentialCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Suny on 7/6/16.
 */
@Controller
public class AuthController {
    private static Logger log = LoggerFactory.getLogger(AuthController.class);
    private final String UTF8 = ";charset=UTF-8";
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "authorize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public AuthResult doAuth(@RequestBody Credential credential){
        return authService.auth(credential);
    }

    @RequestMapping(value = "invalidate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public void invalidate(@RequestBody CredentialCache credentialCache){
        if(credentialCache == null || credentialCache.getName() == null || credentialCache.getToken() == null){
            throw new StapException("credential not provided!");
        }
        authService.invalidateAuthCache(credentialCache);
    }
}
