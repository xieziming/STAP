package com.xieziming.stap.core.testcase.dao;

import com.xieziming.stap.core.testcase.pojo.TestData;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class TestDataDao {
    @Autowired
    private TestDataDefinitionDao testDataDefinitionDao;

    public void add(TestData testData) {
        String sql = "INSERT INTO "+StapDbTables.TEST_DATA+" SET Test_Case_Id=?, Test_Data_Definition_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testData.getTestCaseId(), testData.getTestDataDefinitionId()});
    }

    public void update(TestData testData) {
        String sql = "UPDATE "+StapDbTables.TEST_DATA+" SET Test_Case_Id=?, Test_Data_Definition_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testData.getTestCaseId(), testData.getTestDataDefinitionId(), testData.getId()});
    }

    public void delete(TestData testData) {
        testDataDefinitionDao.deleteById(testData.getTestDataDefinitionId());
        String sql = "DELETE FROM "+StapDbTables.TEST_DATA+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testData.getId()});
    }

    public void deleteById(Integer id) {
        TestData testData = findById(id);
        delete(testData);
    }

    public TestData findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testDataRowMapper);
    }


    public List<TestData> findAll(int testCaseId){
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA + " WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, testDataRowMapper);
    }

    private RowMapper<TestData> testDataRowMapper = new RowMapper<TestData>() {
        public TestData mapRow(ResultSet resultSet, int i) throws SQLException {
            TestData testData = new TestData();
            testData.setId(resultSet.getInt("Id"));
            testData.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            testData.setTestDataDefinitionId(resultSet.getInt("Test_Definition_Id"));
            return testData;
        }
    };
}
