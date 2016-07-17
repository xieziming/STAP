package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.*;
import com.xieziming.stap.core.execution.raw.RawExecution;
import com.xieziming.stap.core.execution.raw.RawExecutionPlan;
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

    public void add(RawExecutionPlan executionPlan) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getRemark(), executionPlan.getStatus()});
    }

    public void update(RawExecutionPlan executionPlan) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getRemark(), executionPlan.getStatus(), executionPlan.getId()});
    }

    public void delete(ExecutionPlan executionPlan) {
        List<RawExecution> rawExecutionList = executionPlan.getRawExecutionList();
        if(rawExecutionList != null){
            for(RawExecution rawExecution : rawExecutionList){
                executionDao.delete(executionDao.findById(rawExecution.getId()));
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

    public RawExecutionPlan findBasicById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawExecutionPlan>() {
            public RawExecutionPlan mapRow(ResultSet resultSet, int i) throws SQLException {
                RawExecutionPlan basicExecutionPlan = new RawExecutionPlan();
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
        RawExecutionPlan basicExecutionPlan = findBasicById(id);
        ExecutionPlan executionPlan = new ExecutionPlan(basicExecutionPlan);

        String sql = "SELECT Id FROM "+ StapDbTables.EXECUTION.toString()+" WHERE Execution_Plan_Id=?";
        List<RawExecution> executionList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlan.getId()}, new RowMapper<RawExecution>() {
            public RawExecution mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionDao.findBasicById(resultSet.getInt("Id"));
            }
        });
        executionPlan.setRawExecutionList(executionList);

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

    public List<RawExecutionPlan> findAllBasic(){
        String sql = "SELECT Id FROM "+StapDbTables.EXECUTION_PLAN.toString();
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<RawExecutionPlan>() {
            public RawExecutionPlan mapRow(ResultSet resultSet, int i) throws SQLException{
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
