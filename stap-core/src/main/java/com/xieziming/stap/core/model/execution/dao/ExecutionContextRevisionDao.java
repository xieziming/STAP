package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.pojo.ExecutionContextRevision;
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
public class ExecutionContextRevisionDao {
    private static Logger logger = LoggerFactory.getLogger(ExecutionContextRevisionDao.class);

    public ExecutionContextRevision findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_REVISION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionContextRevisionRowMapper);
    }

    public List<ExecutionContextRevision> findAll(int executionContextId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_REVISION + " WHERE Execution_Context_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionContextId}, executionContextRevisionRowMapper);
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_REVISION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAll(int executionContextId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_REVISION+" WHERE Execution_Context_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionContextId});
    }


    private RowMapper<ExecutionContextRevision> executionContextRevisionRowMapper = new RowMapper<ExecutionContextRevision>() {
        public ExecutionContextRevision mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionContextRevision executionContextRevision = new ExecutionContextRevision();
            executionContextRevision.setId(resultSet.getInt("Id"));
            executionContextRevision.setExecutionContextId(resultSet.getInt("Execution_Context_Id"));
            executionContextRevision.setType(resultSet.getString("Type"));
            executionContextRevision.setContent(resultSet.getString("Content"));
            executionContextRevision.setTime(resultSet.getTimestamp("Time"));
            return executionContextRevision;
        }
    };
}
