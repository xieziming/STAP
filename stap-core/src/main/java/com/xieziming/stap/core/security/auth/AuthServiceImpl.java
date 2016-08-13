package com.xieziming.stap.core.security.auth;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.xieziming.stap.core.model.user.converter.UserConverter;
import com.xieziming.stap.core.model.user.dao.UserDao;
import com.xieziming.stap.core.model.user.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Suny on 7/6/16.
 */
@Component
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserConverter userConverter;

    private Cache<String, AuthResult> userCache = CacheBuilder.newBuilder()
            .maximumSize(10000L)
            .expireAfterAccess(600, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, AuthResult>() {
                public void onRemoval(RemovalNotification<String, AuthResult> notification) {
                    log.info("User " + notification.getValue().getUserDto().getName() + "'s authorization has expired.");
                }
            })
            .build();

    public AuthResult auth(Credential credential) {
        AuthResult authResult = new AuthResult();
        List<User> userList = userDao.findByCredential(credential);
        if(userList.size() > 0) {
            authResult.setAuthSuccess(true);
            authResult.setUserDto(userConverter.convert(userList.get(0)));
            authResult.setToken(UUID.randomUUID().toString());
            userCache.put(authResult.getUserDto().getName(), authResult);
            log.info("User {} auth successfully and cached with token {}", authResult.getUserDto().getName(), authResult.getToken());
        }else {
            authResult.setAuthSuccess(false);
            authResult.setAuthFailureReason(AuthFailureReason.USER_OR_PASSWORD_INCORRECT);
            log.info("User with credential {} auth failed ", credential.getPrincipal());
        }

        return authResult;
    }

    public AuthResult hasAuth(CredentialCache credentialCache) {
        AuthResult authResult = userCache.getIfPresent(credentialCache.getName());
        if(authResult != null && authResult.getToken().equals(credentialCache.getToken())){
            log.info("user " +credentialCache.getName() +" found in auth cache with token: "+credentialCache.getToken());
            return authResult;
        }else{
            AuthResult failedAuthResult = new AuthResult();
            failedAuthResult.setAuthSuccess(false);
            failedAuthResult.setAuthFailureReason(AuthFailureReason.USER_NOT_AUTHORIZED);
            log.info("user " +credentialCache.getName() +" NOT found in auth cache with token: "+credentialCache.getToken());
            return failedAuthResult;
        }
    }

    public void cacheAuth(AuthResult authResult) {

    }

    public void invalidateAuthCache(CredentialCache credentialCache) {

    }
}
