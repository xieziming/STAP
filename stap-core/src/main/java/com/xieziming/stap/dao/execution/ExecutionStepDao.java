package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.raw.RawExecutionStep;
import com.xieziming.stap.dao.testcase.TestStepDao;
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
    private ExecutionDao executionDao;
    @Autowired
    private TestStepDao testStepDao;
    @Autowired
    private ExecutionStepLogDao executionStepLogDao;
    @Autowired
    private RawExecutionOutputTextDao executionStepOutputTextDao;
    @Autowired
    private ExecutionStepOutputFileDao executionStepOutputFileDao;

    public void add(ExecutionStep executionStep) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION_STEP.toString()+" SET Execution_Id=?, Test_Step_Id=?, Start_Time=?, End_Time=?, Status=?, Result=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStep.getRawExecution().getId(), executionStep.getTestStep().getId(), executionStep.getStartTime(), executionStep.getEndTime(), executionStep.getStatus(), executionStep.getResult(), executionStep.getRemark()});
    }

    public void update(ExecutionStep executionStep) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_STEP.toString()+" SET Execution_Id=?, Test_Step_Id=?, Start_Time=?, End_Time=?, Status=?, Result=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStep.getRawExecution().getId(), executionStep.getTestStep().getId(), executionStep.getStartTime(), executionStep.getEndTime(), executionStep.getStatus(), executionStep.getResult(), executionStep.getRemark(), executionStep.getId()});
    }

    public void delete(ExecutionStep executionStep) {
        List<ExecutionStepLog> executionStepLogList = executionStep.getExecutionStepLogList();
        if(executionStepLogList != null){
            for(ExecutionStepLog executionStepLog : executionStepLogList){
                executionStepLogDao.delete(executionStepLog);
            }
        }

        List<ExecutionStepOutputText> executionStepOutputTextList = executionStep.getExecutionStepOutputTextList();
        if(executionStepOutputTextList != null){
            for(ExecutionStepOutputText executionStepOutputText : executionStepOutputTextList){
                executionStepOutputTextDao.delete(executionStepOutputText);
            }
        }

        List<ExecutionStepOutputFile> executionStepOutputFileList = executionStep.getExecutionStepOutputFileList();
        if(executionStepOutputFileList != null){
            for(ExecutionStepOutputFile executionStepOutputFile : executionStepOutputFileList){
                executionStepOutputFileDao.delete(executionStepOutputFile);
            }
        }

        String sql = "DELETE FROM "+StapDbTables.EXECUTION_STEP.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStep.getId()});
    }

    public RawExecutionStep findBasicById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_STEP.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawExecutionStep>() {
            public RawExecutionStep mapRow(ResultSet resultSet, int i) throws SQLException {
                RawExecutionStep rawExecutionStep = new RawExecutionStep();
                rawExecutionStep.setId(resultSet.getInt("Id"));
                rawExecutionStep.setRawExecution(executionDao.findBasicById(resultSet.getInt("Execution_Id")));
                rawExecutionStep.setTestStep(testStepDao.findById(resultSet.getInt("Test_Step_Id")));
                rawExecutionStep.setStartTime(resultSet.getTimestamp("Start_Time"));
                rawExecutionStep.setEndTime(resultSet.getTimestamp("End_Time"));
                rawExecutionStep.setStatus(resultSet.getString("Status"));
                rawExecutionStep.setResult(resultSet.getString("Result"));
                rawExecutionStep.setRemark(resultSet.getString("Remark"));
                return rawExecutionStep;
            }
        });
    }

    public ExecutionStep findById(int id){
        RawExecutionStep rawExecutionStep = findBasicById(id);
        ExecutionStep executionStep = new ExecutionStep(rawExecutionStep);

        String sql = "SELECT Id FROM "+ StapDbTables.EXECUTION_STEP_LOG.toString()+" WHERE Execution_Step_Id=?";
        List<ExecutionStepLog> executionStepLogList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionStep.getId()}, new RowMapper<ExecutionStepLog>() {
            public ExecutionStepLog mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionStepLogDao.findById(resultSet.getInt("Id"));
            }
        });
        executionStep.setExecutionStepLogList(executionStepLogList);

        sql = "SELECT Id FROM "+ StapDbTables.EXECUTION_OUTPUT_TEXT.toString()+" WHERE Execution_Step_Id=?";
        List<ExecutionStepOutputText> executionStepOutputTextList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionStep.getId()}, new RowMapper<ExecutionStepOutputText>() {
            public ExecutionStepOutputText mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionStepOutputTextDao.findById(resultSet.getInt("Id"));
            }
        });
        executionStep.setExecutionStepOutputTextList(executionStepOutputTextList);

        sql = "SELECT Id FROM "+ StapDbTables.EXECUTION_OUTPUT_FILE.toString()+" WHERE Execution_Step_Id=?";
        List<ExecutionStepOutputFile> executionStepOutputFileList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionStep.getId()}, new RowMapper<ExecutionStepOutputFile>() {
            public ExecutionStepOutputFile mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionStepOutputFileDao.findById(resultSet.getInt("Id"));
            }
        });
        executionStep.setExecutionStepOutputFileList(executionStepOutputFileList);
        return executionStep;
    }
}
