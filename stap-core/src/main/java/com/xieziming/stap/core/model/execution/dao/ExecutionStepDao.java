package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.pojo.ExecutionStep;
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
public class ExecutionStepDao {
    @Autowired
    private ExecutionLogDao executionLogDao;
    @Autowired
    private ExecutionOutputTextDao executionOutputTextDao;
    @Autowired
    private ExecutionOutputFileDao executionOutputFileDao;

    public void add(ExecutionStep executionStep) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION_STEP+" SET Execution_Id=?, Test_Step_Id=?, Start_Time=?, End_Time=?, Status=?, Result=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStep.getExecutionId(), executionStep.getTestStepId(), executionStep.getStartTime(), executionStep.getEndTime(), executionStep.getStatus(), executionStep.getResult(), executionStep.getRemark()});
    }

    public void update(ExecutionStep executionStep) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_STEP+" SET Execution_Id=?, Test_Step_Id=?, Start_Time=?, End_Time=?, Status=?, Result=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStep.getExecutionId(), executionStep.getTestStepId(), executionStep.getStartTime(), executionStep.getEndTime(), executionStep.getStatus(), executionStep.getResult(), executionStep.getRemark(), executionStep.getId()});
    }

    public void delete(ExecutionStep executionStep) {
        delete(executionStep.getId());
    }

    public void delete(int executionStepId) {
        executionLogDao.deleteAllByExecutionStepId(executionStepId);
        executionOutputTextDao.deleteAllByExecutionStepId(executionStepId);
        executionOutputFileDao.deleteAllByExecutionStepId(executionStepId);

        String sql = "DELETE FROM "+StapDbTables.EXECUTION_STEP+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepId});
    }

    public void deleteAllByExecutionId(int executionId) {
        List<ExecutionStep> executionStepList = findAllByExecutionId(executionId);
        for(ExecutionStep executionStep : executionStepList){
            delete(executionStep);
        }

        executionLogDao.deleteAllByExecutionId(executionId);
        executionOutputTextDao.deleteAllByExecutionId(executionId);
        executionOutputFileDao.deleteAllByExecutionId(executionId);
    }

    public ExecutionStep findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_STEP + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id},executionStepRowMapper);
    }

    public List<ExecutionStep> findAllByExecutionId(int executionId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_STEP + " WHERE Execution_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId},executionStepRowMapper);
    }

    private RowMapper<ExecutionStep> executionStepRowMapper = new RowMapper<ExecutionStep>() {
        public ExecutionStep mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionStep executionStep = new ExecutionStep();
            executionStep.setId(resultSet.getInt("Id"));
            executionStep.setExecutionId(resultSet.getInt("Execution_Id"));
            executionStep.setTestStepId(resultSet.getInt("Test_Step_Id"));
            executionStep.setStartTime(resultSet.getTimestamp("Start_Time"));
            executionStep.setEndTime(resultSet.getTimestamp("End_Time"));
            executionStep.setStatus(resultSet.getString("Status"));
            executionStep.setResult(resultSet.getString("Result"));
            executionStep.setRemark(resultSet.getString("Remark"));
            return executionStep;
        }
    };
}
