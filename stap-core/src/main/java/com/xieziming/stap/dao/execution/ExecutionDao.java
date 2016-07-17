package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.raw.RawExecution;
import com.xieziming.stap.core.execution.Execution;
import com.xieziming.stap.core.execution.ExecutionLog;
import com.xieziming.stap.core.execution.ExecutionStep;
import com.xieziming.stap.dao.testcase.raw.RawTestCaseDao;
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
    private RawTestCaseDao testCaseDao;
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private ExecutionContextDao executionContextDao;

    public void add(RawExecution rawExecution) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION.toString()+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Stat_Time=?, End_Time=?, Status=?, Result=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawExecution.getBasicExecutionPlan().getId(), rawExecution.getBasicTestCase().getId(), rawExecution.getExecutionContext().getId(), rawExecution.getStartTime(), rawExecution.getEndTime(), rawExecution.getStatus(), rawExecution.getResult(), rawExecution.getRemark()});
    }

    public void update(RawExecution rawExecution) {
        String sql = "UPDATE "+StapDbTables.EXECUTION.toString()+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Stat_Time=?, End_Time=?, Status=?, Result=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawExecution.getBasicExecutionPlan().getId(), rawExecution.getBasicTestCase().getId(), rawExecution.getExecutionContext().getId(), rawExecution.getStartTime(), rawExecution.getEndTime(), rawExecution.getStatus(), rawExecution.getResult(), rawExecution.getRemark(), rawExecution.getId()});
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

    public RawExecution findBasicById(int id) {
        if(id == 0) {
            logger.error("Try to retrieve channel with null id");
            return null;
        }

        String sql = "SELECT * FROM " + StapDbTables.EXECUTION.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawExecution>() {
            public RawExecution mapRow(ResultSet resultSet, int i) throws SQLException {
                RawExecution rawExecution = new RawExecution();
                rawExecution.setId(resultSet.getInt("Id"));
                rawExecution.setBasicTestCase(testCaseDao.findBasicById(resultSet.getInt("Test_Case_Id")));
                rawExecution.setBasicExecutionPlan(executionPlanDao.findBasicById(resultSet.getInt("Execution_Plan_Id")));
                rawExecution.setExecutionContext(executionContextDao.findById(resultSet.getInt("Execution_Context_Id")));
                rawExecution.setStartTime(resultSet.getTimestamp("Start_Time"));
                rawExecution.setEndTime(resultSet.getTimestamp("End_Time"));
                rawExecution.setStatus(resultSet.getString("Status"));
                rawExecution.setResult(resultSet.getString("Result"));
                rawExecution.setRemark(resultSet.getString("Remark"));
                return rawExecution;
            }
        });
    }

    public Execution findById(int id){
        if(id == 0) {
            logger.error("Try to retrieve channel with null id");
            return null;
        }

        RawExecution rawExecution = findBasicById(id);
        Execution execution = new Execution(rawExecution);

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

    public List<RawExecution> findAllBasic(){
        String sql = "SELECT Id FROM "+StapDbTables.EXECUTION.toString();
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<RawExecution>() {
            public RawExecution mapRow(ResultSet resultSet, int i) throws SQLException{
                return findBasicById(resultSet.getInt("Id"));
            }
        });
    }

    public List<Execution> findAll(){
        String sql = "SELECT Id FROM "+StapDbTables.EXECUTION.toString();
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<Execution>() {
            public Execution mapRow(ResultSet resultSet, int i) throws SQLException{
                return findById(resultSet.getInt("Id"));
            }
        });
    }
}
