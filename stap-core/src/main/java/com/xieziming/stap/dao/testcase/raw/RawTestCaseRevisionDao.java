package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestCaseRevision;
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
public class RawTestCaseRevisionDao {

    public void add(RawTestCaseRevision rawTestCaseRevision) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_REVISION+" SET Test_Case_Id =?, Test_Data_Id=?, Test_Step_Id=?, Test_Action_Id=?, Content=?, Operator=?, `Time`=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCaseRevision.getTestCaseId(), rawTestCaseRevision.getTestDataId(), rawTestCaseRevision.getTestStepId(), rawTestCaseRevision.getTestActionId(), rawTestCaseRevision.getContent(), rawTestCaseRevision.getOperator(), rawTestCaseRevision.getTime()});
    }

    public void update(RawTestCaseRevision rawTestCaseRevision) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_REVISION+" SET Test_Case_Id =?, Test_Data_Id=?, Test_Step_Id=?, Test_Action_Id=?, Content=?, Operator=?, `Time`=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCaseRevision.getTestCaseId(), rawTestCaseRevision.getTestDataId(), rawTestCaseRevision.getTestStepId(), rawTestCaseRevision.getTestActionId(), rawTestCaseRevision.getContent(), rawTestCaseRevision.getOperator(), rawTestCaseRevision.getTime(), rawTestCaseRevision.getId()});
    }

    public void delete(RawTestCaseRevision rawTestCaseRevision) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_REVISION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCaseRevision.getId()});
    }

    public RawTestCaseRevision findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestCaseRevision>() {
            public RawTestCaseRevision mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestCaseRevision rawTestCaseRevision = new RawTestCaseRevision();
                rawTestCaseRevision.setId(resultSet.getInt("Id"));
                rawTestCaseRevision.setTestCaseId(resultSet.getInt("Test_Case_Id"));
                rawTestCaseRevision.setTestDataId(resultSet.getInt("Test_Data_Id"));
                rawTestCaseRevision.setTestStepId(resultSet.getInt("Test_Step_Id"));
                rawTestCaseRevision.setTestActionId(resultSet.getInt("Test_Action_Id"));
                rawTestCaseRevision.setContent(resultSet.getString("Content"));
                rawTestCaseRevision.setOperator(resultSet.getString("Operator"));
                rawTestCaseRevision.setTime(resultSet.getTimestamp("Time"));
                return rawTestCaseRevision;
            }
        });
    }
}
