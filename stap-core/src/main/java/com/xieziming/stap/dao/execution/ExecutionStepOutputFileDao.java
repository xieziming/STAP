package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.ExecutionStepOutputFile;
import com.xieziming.stap.dao.file.StapFileDao;
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
public class ExecutionStepOutputFileDao {
    @Autowired
    private ExecutionStepDao executionStepDao;
    @Autowired
    private StapFileDao stapFileDao;
    public void add(ExecutionStepOutputFile executionStepOutputFile) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_STEP_OUTPUT_FILE.toString()+" SET Execution_Step_Id=?, `Type`=?, File_Id, Remark";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepOutputFile.getBasicExecutionStep().getId(), executionStepOutputFile.getType(), executionStepOutputFile.getStapFile().getId(), executionStepOutputFile.getRemark()});
    }

    public void update(ExecutionStepOutputFile executionStepOutputFile) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_STEP_OUTPUT_FILE.toString()+" SET Execution_Step_Id=?, `Type`=?, File_Id, Remark WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepOutputFile.getBasicExecutionStep().getId(), executionStepOutputFile.getType(), executionStepOutputFile.getStapFile().getId(), executionStepOutputFile.getRemark(), executionStepOutputFile.getId()});
    }

    public void delete(ExecutionStepOutputFile executionStepOutputFile) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_STEP_OUTPUT_FILE.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepOutputFile.getId()});
    }

    public ExecutionStepOutputFile findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_STEP_OUTPUT_FILE.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<ExecutionStepOutputFile>() {
            public ExecutionStepOutputFile mapRow(ResultSet resultSet, int i) throws SQLException {
                ExecutionStepOutputFile executionStepOutputFile = new ExecutionStepOutputFile();
                executionStepOutputFile.setId(resultSet.getInt("Id"));
                executionStepOutputFile.setBasicExecutionStep(executionStepDao.findBasicById(resultSet.getInt("Execution_Step_Id")));
                executionStepOutputFile.setType(resultSet.getString("Type"));
                executionStepOutputFile.setStapFile(stapFileDao.findById(resultSet.getInt("File_Id")));
                executionStepOutputFile.setRemark(resultSet.getString("Remark"));
                executionStepOutputFile.setLastUpDate(resultSet.getTimestamp("Last_Update"));
                return executionStepOutputFile;
            }
        });
    }
}
