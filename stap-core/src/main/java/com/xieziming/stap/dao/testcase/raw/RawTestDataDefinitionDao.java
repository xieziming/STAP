package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestDataDefinition;
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
public class RawTestDataDefinitionDao {

    public void add(RawTestDataDefinition rawTestDataDefinition) {
        String sql = "INSERT INTO "+StapDbTables.TEST_DATA_DEFINITION+" SET Field=?, Value=?, Remark=?, Type=?, File_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestDataDefinition.getField(), rawTestDataDefinition.getValue(), rawTestDataDefinition.getRemark(), rawTestDataDefinition.getType(), rawTestDataDefinition.getFileId()});
    }

    public void update(RawTestDataDefinition rawTestDataDefinition) {
        String sql = "UPDATE "+StapDbTables.TEST_DATA_DEFINITION+" SET Field=?, Value=?, Remark=?, Type=?, File_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestDataDefinition.getField(), rawTestDataDefinition.getValue(), rawTestDataDefinition.getRemark(), rawTestDataDefinition.getType(), rawTestDataDefinition.getFileId(), rawTestDataDefinition.getId()});
    }

    public void delete(RawTestDataDefinition rawTestDataDefinition) {
        String sql = "DELETE FROM "+StapDbTables.TEST_DATA_DEFINITION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestDataDefinition.getId()});
    }

    public RawTestDataDefinition findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA_DEFINITION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestDataDefinition>() {
            public RawTestDataDefinition mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestDataDefinition rawTestDataDefinition = new RawTestDataDefinition();
                rawTestDataDefinition.setId(resultSet.getInt("Id"));
                rawTestDataDefinition.setField(resultSet.getString("Field"));
                rawTestDataDefinition.setValue(resultSet.getString("Value"));
                rawTestDataDefinition.setRemark(resultSet.getString("Remark"));
                rawTestDataDefinition.setType(resultSet.getString("Type"));
                rawTestDataDefinition.setFileId(resultSet.getInt("File_Id"));
                rawTestDataDefinition.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return rawTestDataDefinition;
            }
        });
    }
}
