package com.xieziming.stap.core.model.testcase.dao;

import com.xieziming.stap.core.model.testcase.pojo.TestActionRevision;
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
public class TestActionRevisionDao {
    private static Logger logger = LoggerFactory.getLogger(TestActionRevisionDao.class);

    public TestActionRevision findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testActionRevisionRowMapper);
    }

    public List<TestActionRevision> findAll(int testActionId) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Test_Action_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testActionId}, testActionRevisionRowMapper);
    }

    public List<TestActionRevision> findAllByTestCase(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Test_Action_Id IN (SELECT Test_Action_Id FROM "+StapDbTables.TEST_STEP+" WHERE Test_Case_Id=?)";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, testActionRevisionRowMapper);
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_REVISION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAll(int testActionId) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_REVISION+" WHERE Test_Action_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testActionId});
    }

    private RowMapper<TestActionRevision> testActionRevisionRowMapper = new RowMapper<TestActionRevision>() {
        public TestActionRevision mapRow(ResultSet resultSet, int i) throws SQLException {
            TestActionRevision testActionRevision = new TestActionRevision();
            testActionRevision.setId(resultSet.getInt("Id"));
            testActionRevision.setTestActionId(resultSet.getInt("Test_Action_Id"));
            testActionRevision.setType(resultSet.getString("Type"));
            testActionRevision.setContent(resultSet.getString("Content"));
            testActionRevision.setTime(resultSet.getTimestamp("Time"));
            return testActionRevision;
        }
    };
}
