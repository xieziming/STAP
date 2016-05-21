package com.xieziming.stap.dao.testcase;

import com.xieziming.stap.dao.file.StapFileDao;
import com.xieziming.stap.core.testcase.TestDataDefinition;
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
public class TestDataDefinitionDao {
    @Autowired
    private StapFileDao stapFileDao;

    public void add(TestDataDefinition testDataDefinition) {
        String sql = "INSERT INTO "+StapDbTables.TEST_DATA_DEFINITION.toString()+" SET Field=?, Value=?, Remark=?, Type=?, File_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testDataDefinition.getField(), testDataDefinition.getValue(), testDataDefinition.getRemark(), testDataDefinition.getType(), testDataDefinition.getStapFile().getId()});

    }

    public void update(TestDataDefinition testDataDefinition) {
        String sql = "UPDATE "+StapDbTables.TEST_DATA_DEFINITION.toString()+" SET Field=?, Value=?, Remark=?, Type=?, File_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testDataDefinition.getField(), testDataDefinition.getValue(), testDataDefinition.getRemark(), testDataDefinition.getType(), testDataDefinition.getStapFile().getId(), testDataDefinition.getId()});
    }

    public void delete(TestDataDefinition testDataDefinition) {
        String sql = "DELETE FROM "+StapDbTables.TEST_DATA_DEFINITION.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testDataDefinition.getId()});
    }

    public TestDataDefinition findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA_DEFINITION.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<TestDataDefinition>() {
            public TestDataDefinition mapRow(ResultSet resultSet, int i) throws SQLException {
                TestDataDefinition testDataDefinition = new TestDataDefinition();
                testDataDefinition.setId(resultSet.getInt("Id"));
                testDataDefinition.setField(resultSet.getString("Field"));
                testDataDefinition.setValue(resultSet.getString("Value"));
                testDataDefinition.setRemark(resultSet.getString("Remark"));
                testDataDefinition.setType(resultSet.getString("Type"));
                testDataDefinition.setStapFile(stapFileDao.findById(resultSet.getInt("File_Id")));
                testDataDefinition.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return testDataDefinition;
            }
        });
    }
}
