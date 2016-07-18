package com.xieziming.stap.core.execution.dao;

import com.xieziming.stap.core.execution.pojo.ExecutionOutputText;
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
public class ExecutionOutputTextDao {

    public void add(ExecutionOutputText executionOutputText) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_OUTPUT_TEXT+" SET Execution_Id=?, Execution_Step_Id=?, `Type`=?, Field=?, `Value`=?, Remark";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionOutputText.getExecutionId(), executionOutputText.getExecutionStepId(), executionOutputText.getType(), executionOutputText.getField(), executionOutputText.getValue(), executionOutputText.getRemark()});
    }

    public void update(ExecutionOutputText executionOutputText) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_OUTPUT_TEXT+" SET Execution_Id=?, Execution_Step_Id=?, `Type`=?, Field=?, `Value`=?, Remark WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionOutputText.getExecutionId(), executionOutputText.getExecutionStepId(), executionOutputText.getType(), executionOutputText.getField(), executionOutputText.getValue(), executionOutputText.getRemark(), executionOutputText.getId()});
    }

    public void delete(ExecutionOutputText executionOutputText) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_OUTPUT_TEXT+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionOutputText.getId()});
    }

    public void deleteAllByExecutionId(int executionId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_OUTPUT_TEXT+" WHERE Execution_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }

    public void deleteAllByExecutionStepId(int executionStepId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_OUTPUT_TEXT+" WHERE Execution_Step_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepId});
    }

    public ExecutionOutputText findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_OUTPUT_TEXT + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionOutputTextRowMapper);
    }

    public List<ExecutionOutputText> findByExecution_Id(int executionId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_OUTPUT_TEXT + " WHERE Execution_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId}, executionOutputTextRowMapper);
    }

    public List<ExecutionOutputText> findByExecutionStepId(int executionStepId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_OUTPUT_TEXT + " WHERE Execution_Step_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionStepId}, executionOutputTextRowMapper);
    }

    private RowMapper<ExecutionOutputText> executionOutputTextRowMapper = new RowMapper<ExecutionOutputText>() {
        public ExecutionOutputText mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionOutputText executionOutputText = new ExecutionOutputText();
            executionOutputText.setId(resultSet.getInt("Id"));
            executionOutputText.setExecutionId(resultSet.getInt("Execution_Id"));
            executionOutputText.setExecutionStepId(resultSet.getInt("Execution_Step_Id"));
            executionOutputText.setType(resultSet.getString("Type"));
            executionOutputText.setField(resultSet.getString("Field"));
            executionOutputText.setValue(resultSet.getString("Value"));
            executionOutputText.setRemark(resultSet.getString("Remark"));
            executionOutputText.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return executionOutputText;
        }
    };
}
