package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.pojo.ExecutionPlanMeta;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class ExecutionPlanMetaDao {
    @Autowired
    private ExecutionPlanDao executionPlanDao;

    public void add(ExecutionPlanMeta executionPlanMeta) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_PLAN_META+" SET Execution_Plan_Id=?, Meta_Key=?, Meta_Value=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanMeta.getExecutionPlanId(), executionPlanMeta.getMetaKey(), executionPlanMeta.getMetaValue()});
    }

    public void update(ExecutionPlanMeta executionPlanMeta) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_PLAN_META+" SET Execution_Plan_Id=?, Meta_Key=?, Meta_Value=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanMeta.getExecutionPlanId(), executionPlanMeta.getMetaKey(), executionPlanMeta.getMetaValue(), executionPlanMeta.getId()});
    }

    public void delete(ExecutionPlanMeta executionPlanMeta) {
        delete(executionPlanMeta.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_PLAN_META+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAllByExecutionPlanId(int executionPlanId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_PLAN_META+" WHERE Execution_Plan_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    public ExecutionPlanMeta findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN_META + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionPlanMetaRowMapper);
    }

    public List<ExecutionPlanMeta> findAllByExecutionPlanId(int executionPlanId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_PLAN_META + " WHERE Execution_Plan_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId}, executionPlanMetaRowMapper);
    }

    private RowMapper<ExecutionPlanMeta> executionPlanMetaRowMapper = new RowMapper<ExecutionPlanMeta>() {
        public ExecutionPlanMeta mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionPlanMeta executionPlanMeta = new ExecutionPlanMeta();
            executionPlanMeta.setId(resultSet.getInt("Id"));
            executionPlanMeta.setExecutionPlanId(resultSet.getInt("Execution_Plan_Id"));
            executionPlanMeta.setMetaKey(resultSet.getString("Meta_Key"));
            executionPlanMeta.setMetaValue(resultSet.getString("Meta_Value"));
            executionPlanMeta.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return executionPlanMeta;
        }
    };
}
