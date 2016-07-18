package com.xieziming.stap.core.testcase.dao;

import com.xieziming.stap.core.testcase.pojo.TestCase;
import com.xieziming.stap.core.testcase.pojo.TestCaseRelation;
import com.xieziming.stap.core.testcase.pojo.TestData;
import com.xieziming.stap.core.testcase.pojo.TestStep;
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
public class TestCaseDao {
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;

    @Autowired
    private TestCaseRelationDao testCaseRelationDao;

    @Autowired
    private TestStepDao testStepDao;

    @Autowired
    private TestDataDao testDataDao;

    public void add(TestCase testCase) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE+" SET Name=?, Status=?, Description=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getStatus(), testCase.getDescription()});
    }

    public void update(TestCase testCase) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE+" SET Name=?, Status=?, Description=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getStatus(), testCase.getDescription(), testCase.getId()});
    }

    public void delete(TestCase testCase) {
        List<TestStep> testStepList = testStepDao.findAll(testCase.getId());
        for(TestStep testStep : testStepList){
            testStepDao.delete(testStep);
        }

        List<TestData> testDataList = testDataDao.findAll(testCase.getId());
        for(TestData testData : testDataList){
            testDataDao.delete(testData);
        }

        List<TestCaseRelation> testCaseRelationList = testCaseRelationDao.findAll(testCase.getId());
        for(TestCaseRelation testCaseRelation : testCaseRelationList){
            testCaseRelationDao.delete(testCaseRelation);
        }

        deleteById(testCase.getId());
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public TestCase findById(int id) {
        if(id == 0) {
            return null;
        }

        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<TestCase>() {
            public TestCase mapRow(ResultSet resultSet, int i) throws SQLException {
                TestCase testCase = new TestCase();
                testCase.setId(resultSet.getInt("Id"));
                testCase.setName(resultSet.getString("Name"));
                testCase.setDescription(resultSet.getString("Description"));
                testCase.setStatus(resultSet.getString("Status"));
                testCase.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return testCase;
            }
        });
    }
}
