package com.xieziming.stap.core.model.testcase.dao;

import com.xieziming.stap.core.model.testcase.pojo.TestCaseMeta;
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
public class TestCaseMetaDao {

    public void add(TestCaseMeta testCaseMeta) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_META+" SET Test_Case_Id=?, Meta_Key=?, Meta_Value=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseMeta.getTestCaseId(), testCaseMeta.getMetaKey(), testCaseMeta.getMetaValue()});
    }

    public void update(TestCaseMeta testCaseMeta) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_META+" SET Test_Case_Id=?, Meta_Key=?, Meta_Value=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseMeta.getTestCaseId(), testCaseMeta.getMetaKey(), testCaseMeta.getMetaValue(), testCaseMeta.getId()});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_META+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteByTestCaseId(int testCaseId) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_META+" WHERE Test_Case_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseId});
    }

    public TestCaseMeta findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_META+" WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testCaseMetaRowMapper);
    }

    public List<TestCaseMeta> findAllByTestCaseId(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_META+" WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, testCaseMetaRowMapper);
    }

    private RowMapper<TestCaseMeta> testCaseMetaRowMapper = new RowMapper<TestCaseMeta>() {
        public TestCaseMeta mapRow(ResultSet resultSet, int i) throws SQLException {
            TestCaseMeta testCaseMeta = new TestCaseMeta();
            testCaseMeta.setId(resultSet.getInt("Id"));
            testCaseMeta.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            testCaseMeta.setMetaKey(resultSet.getString("Meta_Key"));
            testCaseMeta.setMetaValue(resultSet.getString("Meta_Value"));
            testCaseMeta.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return testCaseMeta;
        }
    };
}
