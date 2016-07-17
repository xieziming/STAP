package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestData;
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
public class RawTestDataDao {

    public void add(RawTestData rawTestData) {
        String sql = "INSERT INTO "+StapDbTables.TEST_DATA+" SET Test_Case_Id=?, Test_Data_Definition_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestData.getTestCaseId(), rawTestData.getTestDataDefinitionId()});
    }

    public void update(RawTestData rawTestData) {
        String sql = "UPDATE "+StapDbTables.TEST_DATA+" SET Test_Case_Id=?, Test_Data_Definition_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestData.getTestCaseId(),rawTestData.getTestDataDefinitionId(), rawTestData.getId()});
    }

    public void delete(RawTestData rawTestData) {
        String sql = "DELETE FROM "+StapDbTables.TEST_DATA+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestData.getId()});

    }

    public RawTestData findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestData>() {
            public RawTestData mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestData rawTestData = new RawTestData();
                rawTestData.setId(resultSet.getInt("Id"));
                rawTestData.setTestCaseId(resultSet.getInt("Test_Case_Id"));
                rawTestData.setTestDataDefinitionId(resultSet.getInt("Test_Definition_Id"));
                return rawTestData;
            }
        });
    }
}
