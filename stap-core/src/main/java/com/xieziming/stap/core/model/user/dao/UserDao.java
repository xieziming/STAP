package com.xieziming.stap.core.model.user.dao;

import com.xieziming.stap.core.model.user.pojo.User;
import com.xieziming.stap.core.security.auth.Credential;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class UserDao {

    public void add(User user) {
        String sql = "INSERT INTO "+StapDbTables.USER+" SET Name=?, Nick_Name=?, Email=?, Password=?, User_Role_Id=?, Avatar=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{user.getName(), user.getNickName(), user.getEmail(), user.getPassword(), user.getUserRoleId(), user.getAvatar()});
    }

    public void update(User user) {
        String sql = "UPDATE "+StapDbTables.USER+" SET Name=?, Nick_Name=?, Email=?, Password=?, User_Role_Id=?, Avatar=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{user.getName(), user.getNickName(), user.getEmail(), user.getPassword(), user.getUserRoleId(), user.getAvatar(), user.getId()});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.USER+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM " + StapDbTables.USER;
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[0], userRowMapper);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.USER + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, userRowMapper);
    }

    public User findByName(String name) {
        String sql = "SELECT * FROM " + StapDbTables.USER + " WHERE Name=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{name}, userRowMapper);
    }


    public List<User> findByCredential(Credential credential) {
        String sql = "SELECT * FROM " + StapDbTables.USER + " WHERE ( Name=? OR Email=? ) AND Password=?";
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[]{credential.getPrincipal(), credential.getPrincipal(), credential.getPassword()}, userRowMapper);
    }

    RowMapper<User> userRowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("Id"));
            user.setName(resultSet.getString("Name"));
            user.setNickName(resultSet.getString("Nick_Name"));
            user.setEmail(resultSet.getString("Email"));
            user.setPassword(resultSet.getString("Password"));
            user.setUserRoleId(resultSet.getInt("User_Role_Id"));
            user.setAvatar(resultSet.getBytes("Avatar"));
            return user;
        }
    };
}
