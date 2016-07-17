package com.xieziming.stap.dao.testcase.raw;

import com.xieziming.stap.core.testcase.raw.RawTestCaseRelation;
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
public class RawTestCaseRelationDao {

    public void add(RawTestCaseRelation rawBasicTestCaseDependence) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_RELATION+" SET Test_Case_Id=?, Related_Test_Case_Id=?, Relation=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawBasicTestCaseDependence.getTestCaseId(), rawBasicTestCaseDependence.getRelatedTestCaseId(), rawBasicTestCaseDependence.getRemark()});
    }

    public void update(RawTestCaseRelation rawBasicTestCaseDependence) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_RELATION+" SET Test_Case_Id=?, Related_Test_Case_Id=?, Relation=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawBasicTestCaseDependence.getTestCaseId(), rawBasicTestCaseDependence.getRelatedTestCaseId(), rawBasicTestCaseDependence.getRemark(), rawBasicTestCaseDependence.getId()});
    }

    public void delete(RawTestCaseRelation rawBasicTestCaseDependence) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_RELATION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{rawBasicTestCaseDependence.getId()});
    }

    public RawTestCaseRelation findBasicById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_RELATION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawTestCaseRelation>() {
            public RawTestCaseRelation mapRow(ResultSet resultSet, int i) throws SQLException {
                RawTestCaseRelation rawBasicTestCaseDependence = new RawTestCaseRelation();
                rawBasicTestCaseDependence.setId(resultSet.getInt("Id"));
                rawBasicTestCaseDependence.setTestCaseId(resultSet.getInt("Test_Case_Id"));
                rawBasicTestCaseDependence.setRelatedTestCaseId(resultSet.getInt("Related_Test_Case_Id"));
                rawBasicTestCaseDependence.setRemark(resultSet.getString("Remark"));
                return rawBasicTestCaseDependence;
            }
        });
    }
}
