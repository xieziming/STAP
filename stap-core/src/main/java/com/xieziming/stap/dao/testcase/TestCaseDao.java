package com.xieziming.stap.dao.testcase;

import com.xieziming.stap.core.testcase.*;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(TestCaseDao.class);
    @Autowired
    private TestStepDao testStepDao;
    @Autowired
    private TestDataDao testDataDao;
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;

    public void add(BasicTestCase testCase) {
        if(testCase instanceof TestCase) {
            List<TestStep> testStepList = ((TestCase) testCase).getTestStepList();
            if (testStepList != null) {
                for (TestStep testStep : testStepList) {
                    testStepDao.add(testStep);
                }
            }

            List<TestData> testDataList = ((TestCase) testCase).getTestDataList();
            if (testDataList != null) {
                for (TestData testData : testDataList) {
                    testDataDao.add(testData);
                }
            }

            List<TestCaseMeta> testCaseMetaList = ((TestCase) testCase).getTestCaseMetaList();
            if (testCaseMetaList != null) {
                for (TestCaseMeta testCaseMeta : testCaseMetaList) {
                    testCaseMetaDao.add(testCaseMeta);
                }
            }
        }

        String sql = "INSERT INTO "+StapDbTables.TEST_CASE.toString()+" SET Name=?, Parent_Test_Case_Id=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getBasicParentTestCase().getId(), testCase.getRemark()});
    }

    public void update(BasicTestCase testCase) {
        if(testCase instanceof TestCase) {
            List<TestStep> testStepList = ((TestCase) testCase).getTestStepList();
            if (testStepList != null) {
                for (TestStep testStep : testStepList) {
                    testStepDao.update(testStep);
                }
            }

            List<TestData> testDataList = ((TestCase) testCase).getTestDataList();
            if (testDataList != null) {
                for (TestData testData : testDataList) {
                    testDataDao.update(testData);
                }
            }

            List<TestCaseMeta> testCaseMetaList = ((TestCase) testCase).getTestCaseMetaList();
            if (testCaseMetaList != null) {
                for (TestCaseMeta testCaseMeta : testCaseMetaList) {
                    testCaseMetaDao.update(testCaseMeta);
                }
            }
        }

        String sql = "UPDATE "+StapDbTables.TEST_CASE.toString()+" SET Name=?, Parent_Test_Case_Id=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getBasicParentTestCase().getId(), testCase.getRemark(), testCase.getId()});
    }

    public void delete(TestCase testCase) {

        List<TestStep> testStepList = ((TestCase) testCase).getTestStepList();
        if (testStepList != null) {
            for (TestStep testStep : testStepList) {
                testStepDao.delete(testStep);
            }
        }

        List<TestData> testDataList = ((TestCase) testCase).getTestDataList();
        if (testDataList != null) {
            for (TestData testData : testDataList) {
                testDataDao.delete(testData);
            }
        }

        List<TestCaseMeta> testCaseMetaList = ((TestCase) testCase).getTestCaseMetaList();
        if (testCaseMetaList != null) {
            for (TestCaseMeta testCaseMeta : testCaseMetaList) {
                testCaseMetaDao.delete(testCaseMeta);
            }
        }

        String sql = "DELETE FROM "+StapDbTables.TEST_CASE.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getId()});
    }

    public BasicTestCase findBasicById(int id) {
        if(id == 0) {
            return null;
        }

        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE.toString() + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<BasicTestCase>() {
            public BasicTestCase mapRow(ResultSet resultSet, int i) throws SQLException {
                BasicTestCase basicTestCase = new BasicTestCase();
                basicTestCase.setId(resultSet.getInt("Id"));
                basicTestCase.setBasicParentTestCase(findBasicById(resultSet.getInt("Parent_Test_Case_Id")));
                basicTestCase.setName(resultSet.getString("Name"));
                basicTestCase.setRemark(resultSet.getString("Remark"));
                basicTestCase.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return basicTestCase;
            }
        });
    }

    public TestCase findById(int id){
        if(id == 0) {
            return null;
        }

        BasicTestCase basicTestCase = findBasicById(id);
        TestCase testCase = new TestCase(basicTestCase);

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
