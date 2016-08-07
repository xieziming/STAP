package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.pojo.ExecutionPlanRevision;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class ExecutionPlanRevisionDao {
    private static Logger logger = LoggerFactory.getLogger(ExecutionPlanRevisionDao.class);
    public ExecutionPlanRevision findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_REVISION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionPlanRevisionRowMapper);
    }

    public List<ExecutionPlanRevision> findAll(int executionPlanId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_REVISION + " WHERE Execution_Plan_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId}, executionPlanRevisionRowMapper);
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_REVISION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAll(int executionPlanId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_REVISION+" WHERE Execution_Plan_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    private RowMapper<ExecutionPlanRevision> executionPlanRevisionRowMapper = new RowMapper<ExecutionPlanRevision>() {
        public ExecutionPlanRevision mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionPlanRevision executionPlanRevision = new ExecutionPlanRevision();
            executionPlanRevision.setId(resultSet.getInt("Id"));
            executionPlanRevision.setExecutionPlanId(resultSet.getInt("Execution_Plan_Id"));
            executionPlanRevision.setType(resultSet.getString("Type"));
            executionPlanRevision.setContent(resultSet.getString("Content"));
            executionPlanRevision.setTime(resultSet.getTimestamp("Time"));
            return executionPlanRevision;
        }
    };
}
