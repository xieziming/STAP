package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(ExecutionDao.class);

    @Autowired
    private ExecutionStepDao executionStepDao;
    @Autowired
    private ExecutionLogDao executionLogDao;
    @Autowired
    private ExecutionOutputFileDao executionOutputFileDao;
    @Autowired
    private ExecutionOutputTextDao executionOutputTextDao;

    public Execution findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionRowMapper);
    }

    public List<Execution> findAllByExecutionPlanId(int executionPlanId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION + " WHERE Execution_Plan_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId}, executionRowMapper);
    }

    public List<Execution> findAllByTestCaseId(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION + " WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, executionRowMapper);
    }

    public List<Execution> findAll() {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION;
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[0], executionRowMapper);
    }

    public void add(Execution execution) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Start_Time=?, End_Time=?, Status=?, Result=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{execution.getExecutionPlanId(), execution.getTestCaseId(), execution.getExecutionContextId(), execution.getStartTime(), execution.getEndTime(), execution.getStatus(), execution.getResult(), execution.getRemark()});
    }

    public void update(Execution execution) {
        String sql = "UPDATE "+StapDbTables.EXECUTION+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Start_Time=?, End_Time=?, Status=?, Result=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{execution.getExecutionPlanId(), execution.getTestCaseId(), execution.getExecutionContextId(), execution.getStartTime(), execution.getEndTime(), execution.getStatus(), execution.getResult(), execution.getRemark(), execution.getId()});
    }

    public void delete(Execution execution) {
        delete(execution.getId());
    }

    public void delete(int executionId) {
        executionStepDao.deleteAllByExecutionId(executionId);
        executionLogDao.deleteAllByExecutionId(executionId);
        executionOutputFileDao.deleteAllByExecutionId(executionId);
        executionOutputTextDao.deleteAllByExecutionId(executionId);

        String sql = "DELETE FROM "+StapDbTables.EXECUTION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }

    public void deleteAllByExecutionPlanId(int executionPlanId) {
        List<Execution> executionList = findAllByExecutionPlanId(executionPlanId);
        for(Execution execution : executionList) {
            executionStepDao.deleteAllByExecutionId(execution.getId());
            executionLogDao.deleteAllByExecutionId(execution.getId());
            executionOutputFileDao.deleteAllByExecutionId(execution.getId());
            executionOutputTextDao.deleteAllByExecutionId(execution.getId());
        }

        executionLogDao.deleteAllByExecutionPlanId(executionPlanId);

        String sql = "DELETE FROM "+StapDbTables.EXECUTION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    private RowMapper<Execution> executionRowMapper = new RowMapper<Execution>() {
        public Execution mapRow(ResultSet resultSet, int i) throws SQLException {
            Execution execution = new Execution();
            execution.setId(resultSet.getInt("Id"));
            execution.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            execution.setExecutionPlanId(resultSet.getInt("Execution_Plan_Id"));
            execution.setExecutionContextId(resultSet.getInt("Execution_Context_Id"));
            execution.setStartTime(resultSet.getTimestamp("Start_Time"));
            execution.setEndTime(resultSet.getTimestamp("End_Time"));
            execution.setStatus(resultSet.getString("Status"));
            execution.setResult(resultSet.getString("Result"));
            execution.setRemark(resultSet.getString("Remark"));
            return execution;
        }
    };
}
