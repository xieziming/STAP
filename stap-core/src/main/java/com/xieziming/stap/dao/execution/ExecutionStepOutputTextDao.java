package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.ExecutionStepOutputText;
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
public class ExecutionStepOutputTextDao {
    @Autowired
    private ExecutionStepDao executionStepDao;
    @Autowired
    private StapFileDao stapFileDao;
    public void add(ExecutionStepOutputText executionStepOutputText) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_STEP_OUTPUT_TEXT.toString()+" SET Execution_Step_Id=?, `Type`=?, Field=?, `Value`=?, Remark";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepOutputText.getBasicExecutionStep().getId(), executionStepOutputText.getType(), executionStepOutputText.getField(), executionStepOutputText.getValue(), executionStepOutputText.getRemark()});
    }

    public void update(ExecutionStepOutputText executionStepOutputText) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_STEP_OUTPUT_TEXT.toString()+" SET Execution_Step_Id=?, `Type`=?, Field=?, `Value`=?, Remark WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepOutputText.getBasicExecutionStep().getId(), executionStepOutputText.getType(), executionStepOutputText.getField(), executionStepOutputText.getValue(), executionStepOutputText.getRemark(), executionStepOutputText.getId()});
    }

    public void delete(ExecutionStepOutputText executionStepOutputText) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_STEP_OUTPUT_TEXT.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionStepOutputText.getId()});
    }

    public ExecutionStepOutputText findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_STEP_OUTPUT_TEXT.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<ExecutionStepOutputText>() {
            public ExecutionStepOutputText mapRow(ResultSet resultSet, int i) throws SQLException {
                ExecutionStepOutputText executionStepOutputText = new ExecutionStepOutputText();
                executionStepOutputText.setId(resultSet.getInt("Id"));
                executionStepOutputText.setBasicExecutionStep(executionStepDao.findBasicById(resultSet.getInt("Execution_Step_Id")));
                executionStepOutputText.setType(resultSet.getString("Type"));
                executionStepOutputText.setField(resultSet.getString("Field"));
                executionStepOutputText.setValue(resultSet.getString("Value"));
                executionStepOutputText.setRemark(resultSet.getString("Remark"));
                executionStepOutputText.setLastUpDate(resultSet.getTimestamp("Last_Update"));
                return executionStepOutputText;
            }
        });
    }
}
