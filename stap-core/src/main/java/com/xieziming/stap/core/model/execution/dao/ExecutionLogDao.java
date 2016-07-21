package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.pojo.ExecutionLog;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class ExecutionLogDao {
    @Autowired
    private ExecutionDao executionDao;

    public void add(ExecutionLog executionLog) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_LOG+" SET Execution_Plan_Id=?, Execution_Id=?, Execution_Step_Id=?, `Level`=?, Content=?, `Time`=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionLog.getExecutionPlanId(), executionLog.getExecutionId(), executionLog.getExecutionStepId(), executionLog.getLevel(), executionLog.getContent(), executionLog.getTime()});
    }

    public void update(ExecutionLog executionLog) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_LOG+" SET Execution_Plan_Id=?, Execution_Id=?, Execution_Id=?, , `Level`=?, Content=?, `Time`=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionLog.getExecutionPlanId(), executionLog.getExecutionId(), executionLog.getExecutionStepId(), executionLog.getLevel(), executionLog.getContent(), executionLog.getTime(), executionLog.getId()});
    }

    public void delete(ExecutionLog executionLog) {
       deleteById(executionLog.getId());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_LOG+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAllByExecutionPlanId(int executionPlanId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_LOG+" WHERE Execution_Plan_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    public void deleteAllByExecutionId(int executionId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_LOG+" WHERE Execution_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }

    public void deleteAllByExecutionStepId(int executionStepId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_LOG+" WHERE Execution_Step_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepId});
    }

    public ExecutionLog findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_LOG + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionLogRowMapper);
    }

    public List<ExecutionLog> findAllByExecutionPlanId(int executionPlanId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_LOG + " WHERE Execution_Plan_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId}, executionLogRowMapper);
    }

    public List<ExecutionLog> findAllByExecutionId(int executionId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_LOG + " WHERE Execution_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId}, executionLogRowMapper);
    }

    public List<ExecutionLog> findAllByExecutionStepId(int executionStepId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_LOG + " WHERE Execution_Step_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionStepId}, executionLogRowMapper);
    }

    private RowMapper<ExecutionLog> executionLogRowMapper = new RowMapper<ExecutionLog>() {
        public ExecutionLog mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionLog executionLog = new ExecutionLog();
            executionLog.setId(resultSet.getInt("Id"));
            executionLog.setExecutionPlanId(resultSet.getInt("Execution_Plan_Id"));
            executionLog.setExecutionId(resultSet.getInt("Execution_Id"));
            executionLog.setExecutionStepId(resultSet.getInt("Execution_Step_Id"));
            executionLog.setLevel(resultSet.getString("Level"));
            executionLog.setContent(resultSet.getString("Content"));
            executionLog.setTime(resultSet.getTimestamp("Time"));
            return executionLog;
        }
    };
}
