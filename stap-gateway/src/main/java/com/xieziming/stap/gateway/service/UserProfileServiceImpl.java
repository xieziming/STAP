package com.xieziming.stap.gateway.service;

import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import com.xieziming.stap.gateway.mode.UserProfile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/6/16.
 */
@Component
public class UserProfileServiceImpl implements UserProfileService {
    public UserProfile getUserProfile(String principal) {
        String sql = "SELECT u.NAME, u.Nick_Name, u.Email, u.Avatar, r.Name AS Role                                               " +
                     "FROM " + StapDbTables.USER.toString() + " u, "+StapDbTables.USER_ROLE.toString()+" r    " +
                     "WHERE u.Name=? OR u.Email=?                                                                       ";

        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{principal, principal}, new RowMapper<UserProfile>() {
            public UserProfile mapRow(ResultSet resultSet, int i) throws SQLException {
                UserProfile profile = new UserProfile();
                profile.setName(resultSet.getString("Name"));
                profile.setNickName(resultSet.getString("Nick_Name"));
                profile.setEmail(resultSet.getString("Email"));
                profile.setRole(resultSet.getString("Role"));
                profile.setAvatar(resultSet.getString("Avatar"));
                return profile;
            }
        });
    }
}
