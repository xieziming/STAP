package com.xieziming.stap.core.execution.dao;

import com.xieziming.stap.core.execution.pojo.ExecutionOutputFile;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class ExecutionOutputFileDao {

    public void add(ExecutionOutputFile executionOutputFile) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_OUTPUT_FILE+" SET Execution_Id=?, Execution_Step_Id=?, `Type`=?, File_Id, Remark";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionOutputFile.getExecutionId(), executionOutputFile.getExecutionStepId(), executionOutputFile.getType(), executionOutputFile.getFileId(), executionOutputFile.getRemark()});
    }

    public void update(ExecutionOutputFile executionOutputFile) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_OUTPUT_FILE+" SET Execution_Id=?, Execution_Step_Id=?, `Type`=?, File_Id, Remark WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionOutputFile.getExecutionId(), executionOutputFile.getExecutionStepId(), executionOutputFile.getType(), executionOutputFile.getFileId(), executionOutputFile.getRemark(), executionOutputFile.getId()});
    }

    public void delete(ExecutionOutputFile executionOutputFile) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_OUTPUT_FILE+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionOutputFile.getId()});
    }

    public void deleteAllByExecutionId(int executionId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_OUTPUT_FILE+" WHERE Execution_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }


    public void deleteAllByExecutionStepId(int executionStepId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_OUTPUT_FILE+" WHERE Execution_Step_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepId});
    }

    public ExecutionOutputFile findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_OUTPUT_FILE + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, executionOutputFileRowMapper);
    }


    public List<ExecutionOutputFile> findAllByExecution_Id(int executionId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_OUTPUT_FILE + " WHERE Execution_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId}, executionOutputFileRowMapper);
    }

    public List<ExecutionOutputFile> findAllByExecutionStepId(int executionStepId) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_OUTPUT_FILE + " WHERE Execution_Step_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionStepId}, executionOutputFileRowMapper);
    }

    private RowMapper<ExecutionOutputFile> executionOutputFileRowMapper = new RowMapper<ExecutionOutputFile>() {
        public ExecutionOutputFile mapRow(ResultSet resultSet, int i) throws SQLException {
            ExecutionOutputFile executionOutputFile = new ExecutionOutputFile();
            executionOutputFile.setId(resultSet.getInt("Id"));
            executionOutputFile.setExecutionId(resultSet.getInt("Execution_Id"));
            executionOutputFile.setExecutionStepId(resultSet.getInt("Execution_Step_Id"));
            executionOutputFile.setType(resultSet.getString("Type"));
            executionOutputFile.setFileId(resultSet.getInt("File_Id"));
            executionOutputFile.setRemark(resultSet.getString("Remark"));
            executionOutputFile.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return executionOutputFile;
        }
    };
}
