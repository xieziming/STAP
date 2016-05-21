package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.ExecutionPlanLog;
import com.xieziming.stap.dao.common.StapLogDao;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class ExecutionPlanLogDao {
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private StapLogDao stapLogDao;
    public void add(ExecutionPlanLog executionPlanLog) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_PLAN_LOG.toString()+" SET Execution_Plan_d=?, Log_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanLog.getExecutionPlan().getId(), executionPlanLog.getStapLog().getId()});
    }

    public void update(ExecutionPlanLog executionPlanLog) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_PLAN_LOG.toString()+" SET Execution_Plan_Id=?, Log_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanLog.getExecutionPlan().getId(), executionPlanLog.getStapLog().getId(), executionPlanLog.getId()});
    }

    public void delete(ExecutionPlanLog executionPlanLog) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_PLAN_LOG.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanLog.getId()});
    }

    public ExecutionPlanLog findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN_LOG.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<ExecutionPlanLog>() {
            public ExecutionPlanLog mapRow(ResultSet resultSet, int i) throws SQLException {
                ExecutionPlanLog executionPlanLog = new ExecutionPlanLog();
                executionPlanLog.setId(resultSet.getInt("Id"));
                executionPlanLog.setExecutionPlan(executionPlanDao.findById(resultSet.getInt("Execution_Plan_Id")));
                executionPlanLog.setStapLog(stapLogDao.findById(resultSet.getInt("Log_Id")));
                return executionPlanLog;
            }
        });
    }
}
