package com.xieziming.stap.dao.testcase;

import com.xieziming.stap.core.common.StapMeta;
import com.xieziming.stap.core.testcase.TestCaseMeta;
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
public class TestCaseMetaDao {
    @Autowired
    private TestCaseDao testCaseDao;

    public void add(TestCaseMeta testCaseMeta) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_META.toString()+" SET Test_Case_Id=?, Meta_Key=?, Meta_Value=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseMeta.getBasicTestCase().getId(), testCaseMeta.getStapMeta().getMetaKey(), testCaseMeta.getStapMeta().getMetaValue()});
    }

    public void update(TestCaseMeta testCaseMeta) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_META.toString()+" SET Test_Case_Id=?, Meta_Key=?, Meta_Value=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseMeta.getBasicTestCase().getId(), testCaseMeta.getStapMeta().getMetaKey(), testCaseMeta.getStapMeta().getMetaValue(), testCaseMeta.getId()});
    }

    public void delete(TestCaseMeta testCaseMeta) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_META.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseMeta.getId()});
    }

    public TestCaseMeta findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_META.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<TestCaseMeta>() {
            public TestCaseMeta mapRow(ResultSet resultSet, int i) throws SQLException {
                TestCaseMeta testCaseMeta = new TestCaseMeta();
                testCaseMeta.setId(resultSet.getInt("Id"));
                testCaseMeta.setBasicTestCase(testCaseDao.findBasicById(resultSet.getInt("Test_Case_Id")));
                StapMeta stapMeta = new StapMeta();
                stapMeta.setMetaKey(resultSet.getString("Meta_Key"));
                stapMeta.setMetaValue(resultSet.getString("Meta_Value"));
                testCaseMeta.setStapMeta(stapMeta);
                testCaseMeta.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return testCaseMeta;
            }
        });
    }
}
