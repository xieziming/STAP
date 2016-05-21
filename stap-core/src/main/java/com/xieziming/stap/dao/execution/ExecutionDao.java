package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.*;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class ExecutionDao {
    @Autowired
    private ExecutionStepDao executionStepDao;
    @Autowired
    private ExecutionLogDao executionLogDao;

    public void add(Execution execution) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION.toString()+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Stat_Time=?, End_Time=?, Status=?, Result=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{execution.getExecutionPlan().getId(), execution.getTestCase().getId(), execution.getExecutionContext().getId(), execution.getStartTime(), execution.getEndTime(), execution.getStatus(), execution.getResult(), execution.getRemark()});
    }

    public void update(Execution execution) {
        String sql = "UPDATE "+StapDbTables.EXECUTION.toString()+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Stat_Time=?, End_Time=?, Status=?, Result=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{execution.getExecutionPlan().getId(), execution.getTestCase().getId(), execution.getExecutionContext().getId(), execution.getStartTime(), execution.getEndTime(), execution.getStatus(), execution.getResult(), execution.getRemark(), execution.getId()});
    }

    public void delete(Execution execution) {
        List<ExecutionStep> executionStepList = execution.getExecutionStepList();
        if(executionStepList != null){
            for(ExecutionStep executionStep : executionStepList){
                executionStepDao.delete(executionStep);
            }
        }

        List<ExecutionLog> executionLogList = execution.getExecutionLogList();
        if(executionLogList != null) {
            for (ExecutionLog executionLog : executionLogList) {
                executionLogDao.delete(executionLog);
            }
        }

        String sql = "DELETE FROM "+StapDbTables.EXECUTION.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{execution.getId()});
    }

    public Execution findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Execution>() {
            public Execution mapRow(ResultSet resultSet, int i) throws SQLException {
                Execution execution = new Execution();
                execution.setId(resultSet.getInt("Id"));
                execution.setStartTime(resultSet.getTimestamp("Start_Time"));
                execution.setEndTime(resultSet.getTimestamp("End_Time"));
                execution.setStatus(resultSet.getString("Status"));
                execution.setResult(resultSet.getString("Result"));
                execution.setRemark(resultSet.getString("Remark"));
                return execution;
            }
        });
    }

    public Execution fullVersion(Execution execution){
        String sql = "SELECT Id FROM "+ StapDbTables.EXECUTION_STEP.toString()+" WHERE Execution_Id=?";
        List<ExecutionStep> executionStepList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{execution.getId()}, new RowMapper<ExecutionStep>() {
            public ExecutionStep mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionStepDao.findById(resultSet.getInt("Id"));
            }
        });
        execution.setExecutionStepList(executionStepList);

        sql = "SELECT Id FROM "+ StapDbTables.EXECUTION_LOG.toString()+" WHERE Execution_Id=?";
        List<ExecutionLog> executionLogList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{execution.getId()}, new RowMapper<ExecutionLog>() {
            public ExecutionLog mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionLogDao.findById(resultSet.getInt("Id"));
            }
        });
        execution.setExecutionLogList(executionLogList);

        return execution;
    }
}
