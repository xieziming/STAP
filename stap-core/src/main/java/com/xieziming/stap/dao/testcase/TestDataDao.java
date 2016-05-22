package com.xieziming.stap.dao.testcase;

import com.xieziming.stap.core.testcase.TestData;
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
public class TestDataDao {
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private TestDataDefinitionDao testDataDefinitionDao;
    public void add(TestData testData) {
        String sql = "INSERT INTO "+StapDbTables.TEST_DATA.toString()+" SET Test_Case_Id=?, Test_Data_Definition_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testData.getBasicTestCase().getId(), testData.getTestDataDefinition().getId()});
    }

    public void update(TestData testData) {
        String sql = "UPDATE "+StapDbTables.TEST_DATA.toString()+" SET Test_Case_Id=?, Test_Data_Definition_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testData.getBasicTestCase().getId(),testData.getTestDataDefinition().getId(), testData.getId()});
    }

    public void delete(TestData testData) {
        String sql = "DELETE FROM "+StapDbTables.TEST_DATA.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testData.getId()});

    }

    public TestData findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA.toString() + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<TestData>() {
            public TestData mapRow(ResultSet resultSet, int i) throws SQLException {
                TestData testData = new TestData();
                testData.setId(resultSet.getInt("Id"));
                testData.setBasicTestCase(testCaseDao.findBasicById(resultSet.getInt("Test_Case_Id")));
                testData.setTestDataDefinition(testDataDefinitionDao.findById(resultSet.getInt("Test_Definition_Id")));
                testData.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return testData;
            }
        });
    }
}
