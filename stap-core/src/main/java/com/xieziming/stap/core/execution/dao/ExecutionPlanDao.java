package com.xieziming.stap.core.execution.dao;

import com.xieziming.stap.core.execution.pojo.ExecutionPlan;
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
    private ExecutionLogDao executionLogDao;

    public void add(ExecutionPlan executionPlan) {
        String sql = "INSERT INTO "+ StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getDescription(), executionPlan.getStatus()});
    }

    public void update(ExecutionPlan executionPlan) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_PLAN.toString()+" SET Name=?, Remark=?, Status=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlan.getName(), executionPlan.getDescription(), executionPlan.getStatus(), executionPlan.getId()});
    }

    public void delete(ExecutionPlan executionPlan) {
        delete(executionPlan.getId());
    }

    public void delete(int executionPlanId) {
        executionDao.deleteAllByExecutionPlanId(executionPlanId);
        executionLogDao.deleteAllByExecutionPlanId(executionPlanId);
        executionPlanMetaDao.deleteAllByExecutionPlanId(executionPlanId);

        String sql = "DELETE FROM "+StapDbTables.EXECUTION_PLAN.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    public ExecutionPlan findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionPlanRowMapper);
    }

    public List<ExecutionPlan> findAll(){
        String sql = "SELECT * FROM "+StapDbTables.EXECUTION_PLAN;
        return StapDbUtil.getJdbcTemplate().query(sql, executionPlanRowMapper);
    }

    private RowMapper<ExecutionPlan> executionPlanRowMapper = new RowMapper<ExecutionPlan>() {
        public ExecutionPlan mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionPlan executionPlan = new ExecutionPlan();
            executionPlan.setId(resultSet.getInt("Id"));
            executionPlan.setName(resultSet.getString("Name"));
            executionPlan.setDescription(resultSet.getString("Description"));
            executionPlan.setStatus(resultSet.getString("Status"));
            return executionPlan;
        }
    };
}
