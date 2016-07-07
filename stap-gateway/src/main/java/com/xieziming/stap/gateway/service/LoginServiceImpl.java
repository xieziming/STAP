package com.xieziming.stap.gateway.service;

import com.google.common.cache.Cache;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import com.xieziming.stap.gateway.mode.LoginFailureReason;
import com.xieziming.stap.gateway.mode.LoginResult;
import com.xieziming.stap.gateway.mode.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Suny on 7/6/16.
 */
@Component
public class LoginServiceImpl implements LoginService{
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private UserProfileService userProfileService;
    private Cache<String, LoginResult> userCache;

    @Autowired
    public LoginServiceImpl(UserProfileService userProfileService, Cache<String, LoginResult> userCache){
        this.userProfileService = userProfileService;
        this.userCache = userCache;
    }
    public LoginResult login(String principal, String password) {

        LoginResult firstResult = doLogin(principal, password, false);
        if(firstResult.isLoginSuccess()){
            return firstResult;
        }else{
            LoginFailureReason loginFailureReason = firstResult.getFailureReason();
            if(loginFailureReason == LoginFailureReason.USER_NOT_FOUND){
                return doLogin(principal, password, true);
            }
            return firstResult;
        }
    }

    private LoginResult doLogin(String principal, String password, boolean useAlternate){
        LoginResult result = new LoginResult();

        String sql = "SELECT u.NAME, u.Nick_Name, u.Email, r.Name AS Role " +
                        "FROM " + StapDbTables.STAP_USER.toString() + " u, "+StapDbTables.STAP_USER_ROLE.toString()+" r " +
                        "WHERE u.Name=? AND u.Password=?";
        if(useAlternate){
            sql =  "SELECT u.NAME, u.Nick_Name, u.Email, r.Name AS Role " +
                    "FROM " + StapDbTables.STAP_USER.toString() + " u, "+StapDbTables.STAP_USER_ROLE.toString()+" r " +
                    "WHERE u.Email=? AND u.Password=?";
        }


        List<UserProfile> userProfileList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{principal, password}, new RowMapper<UserProfile>() {
            public UserProfile mapRow(ResultSet resultSet, int i) throws SQLException {
                UserProfile profile = new UserProfile();
                profile.setName(resultSet.getString("Name"));
                profile.setNickName(resultSet.getString("Nick_Name"));
                profile.setEmail(resultSet.getString("Email"));
                profile.setRole(resultSet.getString("Role"));
                return profile;
            }
        });

        if(userProfileList.size() > 0) {
            result.setLoginSuccess(true);
            result.setUserProfile(userProfileList.get(0));
            result.setToken(UUID.randomUUID().toString());
        }else {
            result.setLoginSuccess(false);
            result.setFailureReason(LoginFailureReason.PASSWORD_INCORRECT);
        }

        return result;
    }
}
