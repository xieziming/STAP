package com.xieziming.stap.dao.execution;

import com.xieziming.stap.core.execution.raw.RawExecutionOutputText;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class RawExecutionOutputTextDao {

    public void add(RawExecutionOutputText RawExecutionOutputText) {
        String sql = "INSERT INTO "+StapDbTables.EXECUTION_OUTPUT_TEXT.toString()+" SET Execution_Step_Id=?, `Type`=?, Field=?, `Value`=?, Remark";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{RawExecutionOutputText.getRawExecutionStep().getId(), RawExecutionOutputText.getType(), RawExecutionOutputText.getField(), RawExecutionOutputText.getValue(), RawExecutionOutputText.getRemark()});
    }

    public void update(RawExecutionOutputText RawExecutionOutputText) {
        String sql = "UPDATE "+StapDbTables.EXECUTION_OUTPUT_TEXT.toString()+" SET Execution_Step_Id=?, `Type`=?, Field=?, `Value`=?, Remark WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{RawExecutionOutputText.getRawExecutionStep().getId(), RawExecutionOutputText.getType(), RawExecutionOutputText.getField(), RawExecutionOutputText.getValue(), RawExecutionOutputText.getRemark(), RawExecutionOutputText.getId()});
    }

    public void delete(RawExecutionOutputText RawExecutionOutputText) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION_OUTPUT_TEXT.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{RawExecutionOutputText.getId()});
    }

    public RawExecutionOutputText findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.EXECUTION_OUTPUT_TEXT.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawExecutionOutputText>() {
            public RawExecutionOutputText mapRow(ResultSet resultSet, int i) throws SQLException {
                RawExecutionOutputText RawExecutionOutputText = new RawExecutionOutputText();
                RawExecutionOutputText.setId(resultSet.getInt("Id"));
                RawExecutionOutputText.setRawExecutionStep(executionStepDao.findBasicById(resultSet.getInt("Execution_Step_Id")));
                RawExecutionOutputText.setType(resultSet.getString("Type"));
                RawExecutionOutputText.setField(resultSet.getString("Field"));
                RawExecutionOutputText.setValue(resultSet.getString("Value"));
                RawExecutionOutputText.setRemark(resultSet.getString("Remark"));
                RawExecutionOutputText.setLastUpDate(resultSet.getTimestamp("Last_Update"));
                return RawExecutionOutputText;
            }
        });
    }
}
