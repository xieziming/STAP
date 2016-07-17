package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestAction;
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
public class RawTestActionDao {

    public void add(RawTestAction rawTestAction) {
        String sql = "INSERT INTO "+StapDbTables.TEST_ACTION+" SET Name =?, Handler=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestAction.getName(), rawTestAction.getHandler(), rawTestAction.getRemark()});
    }

    public void update(RawTestAction rawTestAction) {
        String sql = "UPDATE "+StapDbTables.TEST_ACTION+" SET Name =?, Handler=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestAction.getName(), rawTestAction.getHandler(), rawTestAction.getRemark(), rawTestAction.getId()});
    }

    public void delete(RawTestAction rawTestAction) {
        String sql = "DELETE FROM "+StapDbTables.TEST_ACTION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestAction.getId()});
    }

    public RawTestAction findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_ACTION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestAction>() {
            public RawTestAction mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestAction rawTestAction = new RawTestAction();
                rawTestAction.setId(resultSet.getInt("Id"));
                rawTestAction.setName(resultSet.getString("Name"));
                rawTestAction.setHandler(resultSet.getString("Handler"));
                rawTestAction.setRemark(resultSet.getString("Remark"));
                rawTestAction.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return rawTestAction;
            }
        });
    }
}
