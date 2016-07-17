package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestCaseMeta;
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
public class RawTestCaseMetaDao {

    public void add(RawTestCaseMeta rawTestCaseMeta) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_META+" SET Test_Case_Id=?, Meta_Key=?, Meta_Value=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCaseMeta.getTestCaseId(), rawTestCaseMeta.getMetaKey(), rawTestCaseMeta.getMetaValue()});
    }

    public void update(RawTestCaseMeta rawTestCaseMeta) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_META+" SET Test_Case_Id=?, Meta_Key=?, Meta_Value=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCaseMeta.getTestCaseId(), rawTestCaseMeta.getMetaKey(), rawTestCaseMeta.getMetaValue(), rawTestCaseMeta.getId()});
    }

    public void delete(RawTestCaseMeta rawTestCaseMeta) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_META+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCaseMeta.getId()});
    }

    public RawTestCaseMeta findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_META+" WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestCaseMeta>() {
            public RawTestCaseMeta mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestCaseMeta rawTestCaseMeta = new RawTestCaseMeta();
                rawTestCaseMeta.setId(resultSet.getInt("Id"));
                rawTestCaseMeta.setTestCaseId(resultSet.getInt("Test_Case_Id"));
                rawTestCaseMeta.setMetaKey(resultSet.getString("Meta_Key"));
                rawTestCaseMeta.setMetaValue(resultSet.getString("Meta_Value"));
                rawTestCaseMeta.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return rawTestCaseMeta;
            }
        });
    }
}
