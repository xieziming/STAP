package com.xieziming.stap.dao.testcase;

import com.xieziming.stap.core.testcase.TestStep;
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
public class TestStepDao {
    @Autowired
    private TestCaseDao testCaseDao;

    @Autowired
    private TestActionDao testActionDao;

    public void add(TestStep testStep) {
        String sql = "INSERT INTO "+StapDbTables.TEST_STEP.toString()+" SET Test_Case_Id =?, Step_Order=?, Test_Action_Id=?, Parameter=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testStep.getBasicTestCase().getId(), testStep.getStepOrder(), testStep.getTestAction().getId(), testStep.getTestStepParameter()});
    }

    public void update(TestStep testStep) {
        String sql = "UPDATE "+StapDbTables.TEST_STEP.toString()+" SET Test_Case_Id =?, Step_Order=?, Test_Action_Id=?, Parameter=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testStep.getBasicTestCase().getId(), testStep.getStepOrder(), testStep.getTestAction().getId(), testStep.getTestStepParameter(), testStep.getId()});
    }

    public void delete(TestStep testStep) {
        String sql = "DELETE FROM "+StapDbTables.TEST_STEP.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testStep.getId()});
    }

    public TestStep findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_STEP.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<TestStep>() {
            public TestStep mapRow(ResultSet resultSet, int i) throws SQLException {
                TestStep testStep = new TestStep();
                testStep.setId(resultSet.getInt("Id"));
                testStep.setStepOrder(resultSet.getInt("Step_Order"));
                testStep.setBasicTestCase(testCaseDao.findBasicById(resultSet.getInt("Test_Case_Id")));
                testStep.setTestAction(testActionDao.findById(resultSet.getInt("Test_Action_Id")));
                testStep.setTestStepParameter(resultSet.getString("Parameter"));
                return testStep;
            }
        });
    }
}
