package com.xieziming.stap.core.model.user.dao;

import com.xieziming.stap.core.model.user.pojo.UserRole;
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
public class UserRoleDao {

    public void add(UserRole userRole) {
        String sql = "INSERT INTO "+StapDbTables.USER_ROLE+" SET Name=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{userRole.getName(), userRole.getRemark()});
    }

    public void update(UserRole userRole) {
        String sql = "UPDATE "+StapDbTables.USER_ROLE+" SET Name=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{userRole.getName(), userRole.getRemark(), userRole.getId()});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.USER_ROLE+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public List<UserRole> findAll() {
        String sql = "SELECT * FROM " + StapDbTables.USER_ROLE;
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[0], userRoleRowMapper);
    }

    public UserRole findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.USER_ROLE + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, userRoleRowMapper);
    }

    RowMapper<UserRole> userRoleRowMapper = new RowMapper<UserRole>() {
        public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
            UserRole userRole = new UserRole();
            userRole.setId(resultSet.getInt("Id"));
            userRole.setName(resultSet.getString("Name"));
            userRole.setRemark(resultSet.getString("Remark"));
            return userRole;
        }
    };
}
