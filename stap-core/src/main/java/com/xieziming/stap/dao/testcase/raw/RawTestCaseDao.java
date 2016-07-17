package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestCase;
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
public class RawTestCaseDao {

    public void add(RawTestCase rawTestCase) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE+" SET Name=?, Status=?, Description=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCase.getName(), rawTestCase.getStatus(), rawTestCase.getDescription()});
    }

    public void update(RawTestCase rawTestCase) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE+" SET Name=?, Status=?, Description=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCase.getName(), rawTestCase.getStatus(), rawTestCase.getDescription(), rawTestCase.getId()});
    }

    public void delete(RawTestCase rawTestCase) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawTestCase.getId()});
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public RawTestCase findById(int id) {
        if(id == 0) {
            return null;
        }

        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestCase>() {
            public RawTestCase mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestCase rawTestCase = new RawTestCase();
                rawTestCase.setId(resultSet.getInt("Id"));
                rawTestCase.setName(resultSet.getString("Name"));
                rawTestCase.setDescription(resultSet.getString("Description"));
                rawTestCase.setStatus(resultSet.getString("Status"));
                rawTestCase.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return rawTestCase;
            }
        });
    }
}
