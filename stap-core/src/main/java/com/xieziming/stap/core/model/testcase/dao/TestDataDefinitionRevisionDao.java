package com.xieziming.stap.core.model.testcase.dao;

import com.xieziming.stap.core.model.testcase.pojo.TestDataDefinitionRevision;
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
public class TestDataDefinitionRevisionDao {
    private static Logger logger = LoggerFactory.getLogger(TestDataDefinitionRevisionDao.class);

    public TestDataDefinitionRevision findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testDataDefinitionRevisionRowMapper);
    }

    public List<TestDataDefinitionRevision> findAll(int testDataDefinitionId) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Test_Data_Definition_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testDataDefinitionId}, testDataDefinitionRevisionRowMapper);
    }

    public List<TestDataDefinitionRevision> findAllByTestCaseId(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Test_Data_Definition_Id IN (SELECT Test_Data_Definition_Id FROM "+StapDbTables.TEST_DATA+" WHERE Test_Case_Id=?)";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, testDataDefinitionRevisionRowMapper);
    }


    public void deleteAll(int testDataDefinitionId) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_REVISION+" WHERE Test_Data_Definition_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testDataDefinitionId});
    }

    private RowMapper<TestDataDefinitionRevision> testDataDefinitionRevisionRowMapper = new RowMapper<TestDataDefinitionRevision>() {
        public TestDataDefinitionRevision mapRow(ResultSet resultSet, int i) throws SQLException {
            TestDataDefinitionRevision testDataDefinitionRevision = new TestDataDefinitionRevision();
            testDataDefinitionRevision.setId(resultSet.getInt("Id"));
            testDataDefinitionRevision.setTestDataDefinitionId(resultSet.getInt("Test_Data_Definition_Id"));
            testDataDefinitionRevision.setType(resultSet.getString("Type"));
            testDataDefinitionRevision.setContent(resultSet.getString("Content"));
            testDataDefinitionRevision.setTime(resultSet.getTimestamp("Time"));
            return testDataDefinitionRevision;
        }
    };
}
