package com.xieziming.stap.core.testcase.dao;

import com.xieziming.stap.core.testcase.pojo.TestCaseRelation;
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
public class TestCaseRelationDao {

    public void add(TestCaseRelation testCaseRelation) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_RELATION+" SET Test_Case_Id=?, Related_Test_Case_Id=?, Relation=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseRelation.getTestCaseId(), testCaseRelation.getRelatedTestCaseId(), testCaseRelation.getRemark()});
    }

    public void update(TestCaseRelation testCaseRelation) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_RELATION+" SET Test_Case_Id=?, Related_Test_Case_Id=?, Relation=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseRelation.getTestCaseId(), testCaseRelation.getRelatedTestCaseId(), testCaseRelation.getRemark(), testCaseRelation.getId()});
    }

    public void delete(TestCaseRelation testCaseRelation) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_RELATION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseRelation.getId()});
    }

    public TestCaseRelation findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_RELATION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testCaseRelationRowMapper);
    }

    public List<TestCaseRelation> findAll(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_RELATION + " WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, testCaseRelationRowMapper);
    }

    private RowMapper<TestCaseRelation> testCaseRelationRowMapper = new RowMapper<TestCaseRelation>() {
        public TestCaseRelation mapRow(ResultSet resultSet, int i) throws SQLException {
            TestCaseRelation testCaseRelation = new TestCaseRelation();
            testCaseRelation.setId(resultSet.getInt("Id"));
            testCaseRelation.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            testCaseRelation.setRelatedTestCaseId(resultSet.getInt("Related_Test_Case_Id"));
            testCaseRelation.setRemark(resultSet.getString("Remark"));
            return testCaseRelation;
        }
    };

}
