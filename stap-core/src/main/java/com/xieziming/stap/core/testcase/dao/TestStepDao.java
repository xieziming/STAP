package com.xieziming.stap.core.testcase.dao;

import com.xieziming.stap.core.testcase.pojo.TestStep;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class TestStepDao {

    public void add(TestStep testStep) {
        String sql = "INSERT INTO "+StapDbTables.TEST_STEP+" SET Test_Case_Id =?, Step_Order=?, Test_Action_Id=?, Parameter=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testStep.getTestCaseId(), testStep.getStepOrder(), testStep.getTestActionId(), testStep.getParameter()});
    }

    public void update(TestStep testStep) {
        String sql = "UPDATE "+StapDbTables.TEST_STEP+" SET Test_Case_Id =?, Step_Order=?, Test_Action_Id=?, Parameter=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testStep.getTestCaseId(), testStep.getStepOrder(), testStep.getTestActionId(), testStep.getParameter(), testStep.getId()});
    }

    public void delete(TestStep testStep) {
        String sql = "DELETE FROM "+StapDbTables.TEST_STEP+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testStep.getId()});
    }

    public TestStep findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_STEP + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testStepRowMapper);
    }

    public List<TestStep> findAll(int testCaseId){
        String sql = "SELECT * FROM " + StapDbTables.TEST_STEP + " WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, testStepRowMapper);
    }

    private RowMapper<TestStep> testStepRowMapper = new RowMapper<TestStep>() {
        public TestStep mapRow(ResultSet resultSet, int i) throws SQLException {
            TestStep testStep = new TestStep();
            testStep.setId(resultSet.getInt("Id"));
            testStep.setStepOrder(resultSet.getInt("Step_Order"));
            testStep.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            testStep.setTestActionId(resultSet.getInt("Test_Action_Id"));
            testStep.setParameter(resultSet.getString("Parameter"));
            return testStep;
        }
    };
}
