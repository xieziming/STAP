package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.ExecutionLog;
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
public class ExecutionLogDao {
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private StapLogDao stapLogDao;
    public void add(ExecutionLog executionLog) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_LOG.toString()+" SET Execution_Id=?, Log_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionLog.getBasicExecution().getId(), executionLog.getStapLog().getId()});
    }

    public void update(ExecutionLog executionLog) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_LOG.toString()+" SET Execution_Id=?, Log_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionLog.getBasicExecution().getId(), executionLog.getStapLog().getId(), executionLog.getId()});
    }

    public void delete(ExecutionLog executionLog) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_LOG.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionLog.getId()});
    }

    public ExecutionLog findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_LOG.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<ExecutionLog>() {
            public ExecutionLog mapRow(ResultSet resultSet, int i) throws SQLException {
                ExecutionLog executionLog = new ExecutionLog();
                executionLog.setId(resultSet.getInt("Id"));
                executionLog.setBasicExecution(executionDao.findBasicById(resultSet.getInt("Execution_Id")));
                executionLog.setStapLog(stapLogDao.findById(resultSet.getInt("Log_Id")));
                return executionLog;
            }
        });
    }
}
