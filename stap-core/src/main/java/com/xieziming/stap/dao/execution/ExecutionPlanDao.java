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

    public void add(ExecutionPlan executionPlan) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getRemark(), executionPlan.getStatus()});
    }

    public void update(ExecutionPlan executionPlan) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getRemark(), executionPlan.getStatus(), executionPlan.getId()});
    }

    public void delete(ExecutionPlan executionPlan) {
        List<Execution> executionList = executionPlan.getExecutionList();
        if(executionList != null){
            for(Execution execution : executionList){
                executionDao.delete(execution);
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

    public ExecutionPlan findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<ExecutionPlan>() {
            public ExecutionPlan mapRow(ResultSet resultSet, int i) throws SQLException {
                ExecutionPlan executionPlan = new ExecutionPlan();
                executionPlan.setId(resultSet.getInt("Id"));
                executionPlan.setName(resultSet.getString("Name"));
                executionPlan.setRemark(resultSet.getString("Remark"));
                executionPlan.setStatus(resultSet.getString("Status"));
                executionPlan.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return executionPlan;
            }
        });
    }

    public ExecutionPlan fullVersion(ExecutionPlan executionPlan){
        String sql = "SELECT Id FROM "+ StapDbTables.EXECUTION.toString()+" WHERE Execution_Plan_Id=?";
        List<Execution> executionList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlan.getId()}, new RowMapper<Execution>() {
            public Execution mapRow(ResultSet resultSet, int i) throws SQLException {
                return executionDao.findById(resultSet.getInt("Id"));
            }
        });
        executionPlan.setExecutionList(executionList);

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
}
