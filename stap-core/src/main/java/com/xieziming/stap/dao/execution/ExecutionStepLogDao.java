package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.ExecutionStepLog;
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
public class ExecutionStepLogDao {
    @Autowired
    private ExecutionStepDao executionStepDao;
    @Autowired
    private StapLogDao stapLogDao;
    public void add(ExecutionStepLog executionStepLog) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_STEP_LOG.toString()+" SET Execution_Step_Id=?, Log_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepLog.getExecutionStep().getId(), executionStepLog.getStapLog().getId()});
    }

    public void update(ExecutionStepLog executionStepLog) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_STEP_LOG.toString()+" SET Execution_Step_Id=?, Log_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepLog.getExecutionStep().getId(), executionStepLog.getStapLog().getId(), executionStepLog.getId()});
    }

    public void delete(ExecutionStepLog executionStepLog) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_STEP_LOG.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepLog.getId()});
    }

    public ExecutionStepLog findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_STEP_LOG.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<ExecutionStepLog>() {
            public ExecutionStepLog mapRow(ResultSet resultSet, int i) throws SQLException {
                ExecutionStepLog executionStepLog = new ExecutionStepLog();
                executionStepLog.setId(resultSet.getInt("Id"));
                executionStepLog.setExecutionStep(executionStepDao.findById(resultSet.getInt("Execution_Step_Id")));
                executionStepLog.setStapLog(stapLogDao.findById(resultSet.getInt("Log_Id")));
                return executionStepLog;
            }
        });
    }
}
