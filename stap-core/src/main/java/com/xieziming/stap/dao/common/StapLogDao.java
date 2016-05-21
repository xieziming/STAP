package com.xieziming.stap.dao.common;

import com.xieziming.stap.core.common.StapLog;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class StapLogDao {
    public void add(StapLog stapLog) {
        String sql = "INSERT INTO "+StapDbTables.LOG.toString()+" SET Level=?, Content=?, `Time`=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{stapLog.getLevel(), stapLog.getContent(), stapLog.getTime()});
    }

    public void update(StapLog stapLog) {
        String sql = "UPDATE "+StapDbTables.LOG.toString()+" SET Level=?, Content=?, `Time`=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{stapLog.getLevel(), stapLog.getContent(), stapLog.getTime(), stapLog.getId()});
    }

    public void delete(StapLog stapLog) {
        String sql = "DELETE FROM "+StapDbTables.LOG.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{stapLog.getId()});
    }

    public StapLog findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.LOG.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<StapLog>() {
            public StapLog mapRow(ResultSet resultSet, int i) throws SQLException {
                StapLog stapLog = new StapLog();
                stapLog.setId(resultSet.getInt("Id"));
                stapLog.setLevel(resultSet.getString("Level"));
                stapLog.setContent(resultSet.getString("Content"));
                stapLog.setTime(resultSet.getTimestamp("Time"));
                return stapLog;
            }
        });
    }
}
