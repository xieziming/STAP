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
public class ExecutionPlanDao {
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private ExecutionPlanMetaDao executionPlanMetaDao;
    @Autowired
    private ExecutionPlanLogDao executionPlanLogDao;

    public void add(BasicExecutionPlan executionPlan) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getRemark(), executionPlan.getStatus()});
    }

    public void update(BasicExecutionPlan executionPlan) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getRemark(), executionPlan.getStatus(), executionPlan.getId()});
    }

    public void delete(ExecutionPlan executionPlan) {
        List<BasicExecution> basicExecutionList = executionPlan.getBasicExecutionList();
        if(basicExecutionList != null){
            for(BasicExecution basicExecution : basicExecutionList){
                executionDao.delete(executionDao.findById(basicExecution.getId()));
            }
        }

        List<ExecutionPlanMeta> executionPlanMetaList = executionPlan.getExecutionPlanMetaList();
        if(executionPlanMetaList != null){
            for(ExecutionPlanMeta executionPlanMeta : executionPlanMetaList){
                executionPlanMetaDao.delete(executionPlanMeta);
            }
        }

        List<ExecutionPlanLog> executionPlanLogList = executionPlan.getExecutionPlanLogList();
        if(executionPlanLogList != null){
            for(ExecutionPlanLog executionPlanLog : executionPlanLogList){
                executionPlanLogDao.delete(executionPlanLog);
            }
        }

        String sql = "DELETE FROM "+StapDbTables.EXECUTION_PLAN.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getId()});
    }

    public BasicExecutionPlan findBasicById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<BasicExecutionPlan>() {
            public BasicExecutionPlan mapRow(ResultSet resultSet, int i) throws SQLException {
                BasicExecutionPlan basicExecutionPlan = new BasicExecutionPlan();
                basicExecutionPlan.setId(resultSet.getInt("Id"));
                basicExecutionPlan.setName(resultSet.getString("Name"));
                basicExecutionPlan.setRemark(resultSet.getString("Remark"));
                basicExecutionPlan.setStatus(resultSet.getString("Status"));
                basicExecutionPlan.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return basicExecutionPlan;
            }
        });
    }

    public ExecutionPlan findById(int id){
        BasicExecutionPlan basicExecutionPlan = findBasicById(id);
        ExecutionPlan executionPlan = new ExecutionPlan(basicExecutionPlan);

        String sql = "SELECT Id FROM "+ StapDbTables.EXECUTION.toString()+" WHERE Execution_Plan_Id=?";
        List<BasicExecution> executionList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlan.getId()}, new RowMapper<BasicExecution>() {
            public BasicExecution mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionDao.findBasicById(resultSet.getInt("Id"));
            }
        });
        executionPlan.setBasicExecutionList(executionList);

        sql = "SELECT Id FROM "+ StapDbTables.EXECUTION_PLAN_META.toString()+" WHERE Execution_Plan_Id=?";
        List<ExecutionPlanMeta> executionPlanMetaList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlan.getId()}, new RowMapper<ExecutionPlanMeta>() {
            public ExecutionPlanMeta mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionPlanMetaDao.findById(resultSet.getInt("Id"));
            }
        });
        executionPlan.setExecutionPlanMetaList(executionPlanMetaList);


        sql = "SELECT Id FROM "+ StapDbTables.EXECUTION_PLAN_LOG.toString()+" WHERE Execution_Plan_Id=?";
        List<ExecutionPlanLog> executionPlanLogList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlan.getId()}, new RowMapper<ExecutionPlanLog>() {
            public ExecutionPlanLog mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionPlanLogDao.findById(resultSet.getInt("Id"));
            }
        });
        executionPlan.setExecutionPlanLogList(executionPlanLogList);
        return executionPlan;
    }

    public List<BasicExecutionPlan> findAllBasic(){
        String sql = "SELECT Id FROM "+StapDbTables.EXECUTION_PLAN.toString();
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<BasicExecutionPlan>() {
            public BasicExecutionPlan mapRow(ResultSet resultSet, int i) throws SQLException{
                return findBasicById(resultSet.getInt("Id"));
            }
        });
    }

    public List<ExecutionPlan> findAll(){
        String sql = "SELECT Id FROM "+StapDbTables.EXECUTION_PLAN.toString();
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<ExecutionPlan>() {
            public ExecutionPlan mapRow(ResultSet resultSet, int i) throws SQLException{
                return findById(resultSet.getInt("Id"));
            }
        });
    }
}
