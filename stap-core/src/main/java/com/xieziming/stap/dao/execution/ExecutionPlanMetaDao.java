package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.common.StapMeta;
import com.xieziming.stap.core.execution.ExecutionPlanMeta;
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
public class ExecutionPlanMetaDao {
    @Autowired
    private ExecutionPlanDao executionPlanDao;

    public void add(ExecutionPlanMeta executionPlanMeta) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_PLAN_META.toString()+" SET Execution_Plan_Id=?, Meta_Key=?, Meta_Value=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanMeta.getBasicExecutionPlan().getId(), executionPlanMeta.getStapMeta().getMetaKey(), executionPlanMeta.getStapMeta().getMetaValue()});
    }

    public void update(ExecutionPlanMeta executionPlanMeta) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_PLAN_META.toString()+" SET Execution_Plan_Id=?, Meta_Key=?, Meta_Value=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanMeta.getBasicExecutionPlan().getId(), executionPlanMeta.getStapMeta().getMetaKey(), executionPlanMeta.getStapMeta().getMetaValue(), executionPlanMeta.getId()});
    }

    public void delete(ExecutionPlanMeta executionPlanMeta) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_PLAN_META.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanMeta.getId()});
    }

    public ExecutionPlanMeta findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN_META.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<ExecutionPlanMeta>() {
            public ExecutionPlanMeta mapRow(ResultSet resultSet, int i) throws SQLException {
                ExecutionPlanMeta executionPlanMeta = new ExecutionPlanMeta();
                executionPlanMeta.setId(resultSet.getInt("Id"));
                executionPlanMeta.setBasicExecutionPlan(executionPlanDao.findBasicById(resultSet.getInt("Execution_Plan_Id")));
                executionPlanMeta.setStapMeta(new StapMeta(resultSet.getString("Meta_Key"), resultSet.getString("Meta_Value")));
                executionPlanMeta.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return executionPlanMeta;
            }
        });
    }
}
