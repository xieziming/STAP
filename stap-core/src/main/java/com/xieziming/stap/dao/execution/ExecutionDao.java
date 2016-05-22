package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.BasicExecution;
import com.xieziming.stap.core.execution.Execution;
import com.xieziming.stap.core.execution.ExecutionLog;
import com.xieziming.stap.core.execution.ExecutionStep;
import com.xieziming.stap.dao.testcase.TestCaseDao;
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
    private TestCaseDao testCaseDao;
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private ExecutionContextDao executionContextDao;

    public void add(BasicExecution basicExecution) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION.toString()+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Stat_Time=?, End_Time=?, Status=?, Result=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{basicExecution.getBasicExecutionPlan().getId(), basicExecution.getBasicTestCase().getId(), basicExecution.getExecutionContext().getId(), basicExecution.getStartTime(), basicExecution.getEndTime(), basicExecution.getStatus(), basicExecution.getResult(), basicExecution.getRemark()});
    }

    public void update(BasicExecution basicExecution) {
        String sql = "UPDATE "+StapDbTables.EXECUTION.toString()+" SET Execution_Plan_Id=?, Test_Case_Id=?, Execution_Context_Id=?, Stat_Time=?, End_Time=?, Status=?, Result=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{basicExecution.getBasicExecutionPlan().getId(), basicExecution.getBasicTestCase().getId(), basicExecution.getExecutionContext().getId(), basicExecution.getStartTime(), basicExecution.getEndTime(), basicExecution.getStatus(), basicExecution.getResult(), basicExecution.getRemark(), basicExecution.getId()});
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

    public BasicExecution findBasicById(int id) {
        if(id == 0) {
            logger.error("Try to retrieve execution with null id");
            return null;
        }

        String sql = "SELECT * FROM " + StapDbTables.EXECUTION.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<BasicExecution>() {
            public BasicExecution mapRow(ResultSet resultSet, int i) throws SQLException {
                BasicExecution basicExecution = new BasicExecution();
                basicExecution.setId(resultSet.getInt("Id"));
                basicExecution.setBasicTestCase(testCaseDao.findBasicById(resultSet.getInt("Test_Case_Id")));
                basicExecution.setBasicExecutionPlan(executionPlanDao.findBasicById(resultSet.getInt("Execution_Plan_Id")));
                basicExecution.setExecutionContext(executionContextDao.findById(resultSet.getInt("Execution_Context_Id")));
                basicExecution.setStartTime(resultSet.getTimestamp("Start_Time"));
                basicExecution.setEndTime(resultSet.getTimestamp("End_Time"));
                basicExecution.setStatus(resultSet.getString("Status"));
                basicExecution.setResult(resultSet.getString("Result"));
                basicExecution.setRemark(resultSet.getString("Remark"));
                return basicExecution;
            }
        });
    }

    public Execution findById(int id){
        if(id == 0) {
            logger.error("Try to retrieve execution with null id");
            return null;
        }

        BasicExecution basicExecution = findBasicById(id);
        Execution execution = new Execution(basicExecution);

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

    public List<BasicExecution> findAllBasic(){
        String sql = "SELECT Id FROM "+StapDbTables.EXECUTION.toString();
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<BasicExecution>() {
            public BasicExecution mapRow(ResultSet resultSet, int i) throws SQLException{
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
