package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.pojo.ExecutionContext;
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
public class ExecutionContextDao {
    public void add(ExecutionContext executionContext) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_CONTEXT+" SET Name=?, Content=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionContext.getName(), executionContext.getContent()});

    }

    public void update(ExecutionContext executionContext) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_CONTEXT+" SET Name=?, Content=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionContext.getName(), executionContext.getContent(), executionContext.getId()});
    }

    public void delete(ExecutionContext executionContext) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_CONTEXT+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionContext.getId()});
    }

    public List<ExecutionContext> findAll(){
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_CONTEXT;
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[0], executionContextRowMapper);
    }

    public ExecutionContext findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_CONTEXT + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionContextRowMapper);
    }

    private RowMapper<ExecutionContext> executionContextRowMapper = new RowMapper<ExecutionContext>() {
        public ExecutionContext mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionContext executionContext = new ExecutionContext();
            executionContext.setId(resultSet.getInt("Id"));
            executionContext.setName(resultSet.getString("Name"));
            executionContext.setContent(resultSet.getString("Content"));
            executionContext.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return executionContext;
        }
    };
}
