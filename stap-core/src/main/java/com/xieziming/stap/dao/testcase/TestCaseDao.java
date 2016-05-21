package com.xieziming.stap.dao.testcase;

import com.xieziming.stap.core.testcase.TestCase;
import com.xieziming.stap.core.testcase.TestCaseMeta;
import com.xieziming.stap.core.testcase.TestData;
import com.xieziming.stap.core.teststep.TestStep;
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
    private TestStepDao testStepDao;
    private TestDataDao testDataDao;
    private TestCaseMetaDao testCaseMetaDao;

    public void add(TestCase testCase) {
        List<TestStep> testStepList = testCase.getTestStepList();
        if(testStepList != null) {
            for (TestStep testStep : testStepList) {
                testStepDao.add(testStep);
            }
        }

        List<TestData> testDataList = testCase.getTestDataList();
        if(testDataList != null) {
            for (TestData testData : testDataList) {
                testDataDao.add(testData);
            }
        }

        List<TestCaseMeta> testCaseMetaList = testCase.getTestCaseMetaList();
        if(testCaseMetaList != null){
            for(TestCaseMeta testCaseMeta : testCaseMetaList){
                testCaseMetaDao.add(testCaseMeta);
            }
        }

        String sql = "INSERT INTO "+StapDbTables.TEST_CASE.toString()+" SET Name=?, Parent_Test_Case_Id=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getParentTestCase().getId(), testCase.getRemark()});
    }

    public void update(TestCase testCase) {
        List<TestStep> testStepList = testCase.getTestStepList();
        if(testStepList != null) {
            for(TestStep testStep : testStepList){
                testStepDao.update(testStep);
            }
        }

        List<TestData> testDataList = testCase.getTestDataList();
        if(testDataList != null) {
            for (TestData testData : testDataList) {
                testDataDao.update(testData);
            }
        }

        List<TestCaseMeta> testCaseMetaList = testCase.getTestCaseMetaList();
        if(testCaseMetaList != null) {
            for (TestCaseMeta testCaseMeta : testCaseMetaList) {
                testCaseMetaDao.update(testCaseMeta);
            }
        }

        String sql = "UPDATE "+StapDbTables.TEST_CASE.toString()+" SET Name=?, Parent_Test_Case_Id=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getParentTestCase().getId(), testCase.getRemark(), testCase.getId()});
    }

    public void delete(TestCase testCase) {
        List<TestStep> testStepList = testCase.getTestStepList();
        if(testStepList != null) {
            for (TestStep testStep : testStepList) {
                testStepDao.delete(testStep);
            }
        }

        List<TestData> testDataList = testCase.getTestDataList();
        if(testDataList != null) {
            for (TestData testData : testDataList) {
                testDataDao.delete(testData);
            }
        }

        List<TestCaseMeta> testCaseMetaList = testCase.getTestCaseMetaList();
        if(testCaseMetaList != null) {
            for (TestCaseMeta testCaseMeta : testCaseMetaList) {
                testCaseMetaDao.delete(testCaseMeta);
            }
        }

        String sql = "DELETE FROM "+StapDbTables.TEST_CASE.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getId()});
    }

    public TestCase findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE.toString() + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<TestCase>() {
            public TestCase mapRow(ResultSet resultSet, int i) throws SQLException {
                TestCase testCase = new TestCase();
                testCase.setId(resultSet.getInt("Id"));
                if (resultSet.getObject("Parent_Test_Case_Id") != null)
                    testCase.setParentTestCase(findById(resultSet.getInt("Parent_Test_Case_Id")));
                testCase.setName(resultSet.getString("Name"));
                testCase.setRemark(resultSet.getString("Remark"));
                testCase.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return testCase;
            }
        });
    }

    public TestCase fullVersion(TestCase testCase){
        String sql = "SELECT Id FROM "+ StapDbTables.TEST_STEP.toString()+" WHERE Test_Case_Id=?";
        List<TestStep> testStepList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCase.getId()}, new RowMapper<TestStep>() {
            public TestStep mapRow(ResultSet resultSet, int i) throws SQLException {
                return testStepDao.findById(resultSet.getInt("Id"));
            }
        });
        testCase.setTestStepList(testStepList);

        sql = "SELECT Id FROM "+ StapDbTables.TEST_DATA.toString()+" WHERE Test_Case_Id=?";
        List<TestData> testDataList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCase.getId()}, new RowMapper<TestData>() {
            public TestData mapRow(ResultSet resultSet, int i) throws SQLException {
                return testDataDao.findById(resultSet.getInt("Id"));
            }
        });
        testCase.setTestDataList(testDataList);

        sql = "SELECT Id FROM "+ StapDbTables.TEST_STEP.toString()+" WHERE Test_Case_Id=?";
        List<TestCaseMeta> testCaseMetaList = StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCase.getId()}, new RowMapper<TestCaseMeta>() {
            public TestCaseMeta mapRow(ResultSet resultSet, int i) throws SQLException {
                return testCaseMetaDao.findById(resultSet.getInt("Id"));
            }
        });
        testCase.setTestCaseMetaList(testCaseMetaList);

        return testCase;
    }
}
