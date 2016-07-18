package com.xieziming.stap.core.model.testcase.dao;

import com.xieziming.stap.core.model.testcase.pojo.TestAction;
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
public class TestActionDao {

    public void add(TestAction testAction) {
        String sql = "INSERT INTO "+StapDbTables.TEST_ACTION+" SET Name =?, Handler=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testAction.getName(), testAction.getHandler(), testAction.getRemark()});
    }

    public void update(TestAction testAction) {
        String sql = "UPDATE "+StapDbTables.TEST_ACTION+" SET Name =?, Handler=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testAction.getName(), testAction.getHandler(), testAction.getRemark(), testAction.getId()});
    }

    public void delete(TestAction testAction) {
        String sql = "DELETE FROM "+StapDbTables.TEST_ACTION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testAction.getId()});
    }

    public TestAction findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_ACTION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<TestAction>() {
            public TestAction mapRow(ResultSet resultSet, int i) throws SQLException {
                TestAction testAction = new TestAction();
                testAction.setId(resultSet.getInt("Id"));
                testAction.setName(resultSet.getString("Name"));
                testAction.setHandler(resultSet.getString("Handler"));
                testAction.setRemark(resultSet.getString("Remark"));
                testAction.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return testAction;
            }
        });
    }
}
