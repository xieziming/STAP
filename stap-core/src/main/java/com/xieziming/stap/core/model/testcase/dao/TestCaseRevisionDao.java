package com.xieziming.stap.core.model.testcase.dao;

import com.xieziming.stap.core.model.testcase.pojo.TestCaseRevision;
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
public class TestCaseRevisionDao {
    private static Logger logger = LoggerFactory.getLogger(TestCaseRevisionDao.class);

    public TestCaseRevision findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testCaseRevisionRowMapper);
    }

    public List<TestCaseRevision> findAll(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, testCaseRevisionRowMapper);
    }

    public void deleteAll(int testCaseId) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_REVISION+" WHERE Test_Case_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseId});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_REVISION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    private RowMapper<TestCaseRevision> testCaseRevisionRowMapper = new RowMapper<TestCaseRevision>() {
        public TestCaseRevision mapRow(ResultSet resultSet, int i) throws SQLException {
            TestCaseRevision testCaseRevision = new TestCaseRevision();
            testCaseRevision.setId(resultSet.getInt("Id"));
            testCaseRevision.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            testCaseRevision.setType(resultSet.getString("Type"));
            testCaseRevision.setContent(resultSet.getString("Content"));
            testCaseRevision.setTime(resultSet.getTimestamp("Time"));
            return testCaseRevision;
        }
    };
}
