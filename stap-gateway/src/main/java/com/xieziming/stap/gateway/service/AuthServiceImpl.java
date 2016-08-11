package com.xieziming.stap.gateway.service;

import com.google.common.cache.Cache;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import com.xieziming.stap.gateway.mode.AuthFailureReason;
import com.xieziming.stap.gateway.mode.AuthResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Suny on 7/6/16.
 */
@Component
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private UserProfileService userProfileService;
    private Cache<String, AuthResult> userCache;

    @Autowired
    public AuthServiceImpl(UserProfileService userProfileService, Cache<String, AuthResult> userCache){
        this.userProfileService = userProfileService;
        this.userCache = userCache;
    }
    public AuthResult auth(String principal, String password) {
        log.info("auth user: "+principal);

        AuthResult result = new AuthResult();

        String sql = "SELECT COUNT(*) FROM " + StapDbTables.USER.toString() + " WHERE ( Name=? OR Email=? ) AND Password=?";

        int records = StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{principal, principal, password}, Integer.class);
        if(records > 0) {
            result.setAuthSuccess(true);
            result.setUserProfile(userProfileService.getUserProfile(principal));
            result.setToken(UUID.randomUUID().toString());
        }else {
            result.setAuthSuccess(false);
            result.setAuthFailureReason(AuthFailureReason.USER_OR_PASSWORD_INCORRECT.toString());
        }

        return result;
    }
}
