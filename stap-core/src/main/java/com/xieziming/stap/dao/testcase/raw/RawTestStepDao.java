package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestStep;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class RawTestStepDao {

    public void add(RawTestStep rawTestStep) {
        String sql = "INSERT INTO "+StapDbTables.TEST_STEP+" SET Test_Case_Id =?, Step_Order=?, Test_Action_Id=?, Parameter=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestStep.getTestCaseId(), rawTestStep.getStepOrder(), rawTestStep.getTestActionId(), rawTestStep.getParameter()});
    }

    public void update(RawTestStep rawTestStep) {
        String sql = "UPDATE "+StapDbTables.TEST_STEP+" SET Test_Case_Id =?, Step_Order=?, Test_Action_Id=?, Parameter=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestStep.getTestCaseId(), rawTestStep.getStepOrder(), rawTestStep.getTestActionId(), rawTestStep.getParameter(), rawTestStep.getId()});
    }

    public void delete(RawTestStep rawTestStep) {
        String sql = "DELETE FROM "+StapDbTables.TEST_STEP+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestStep.getId()});
    }

    public RawTestStep findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_STEP + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestStep>() {
            public RawTestStep mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestStep rawTestStep = new RawTestStep();
                rawTestStep.setId(resultSet.getInt("Id"));
                rawTestStep.setStepOrder(resultSet.getInt("Step_Order"));
                rawTestStep.setTestCaseId(resultSet.getInt("Test_Case_Id"));
                rawTestStep.setTestActionId(resultSet.getInt("Test_Action_Id"));
                rawTestStep.setParameter(resultSet.getString("Parameter"));
                return rawTestStep;
            }
        });
    }
}
