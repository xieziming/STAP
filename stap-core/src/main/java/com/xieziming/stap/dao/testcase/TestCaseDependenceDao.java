package com.xieziming.stap.dao.testcase;

import com.xieziming.stap.core.testcase.BasicTestCaseDependence;
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
public class TestCaseDependenceDao {
    @Autowired
    private TestCaseDao testCaseDao;

    public void add(BasicTestCaseDependence basicTestCaseDependence) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_DEPENDENCE.toString()+" SET Test_Case_Id=?, Dependent_Test_Case_Id=?, Remark=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{basicTestCaseDependence.getBasicTestCase().getId(), basicTestCaseDependence.getBasicDependentTestCase().getId(), basicTestCaseDependence.getRemark()});
    }

    public void update(BasicTestCaseDependence basicTestCaseDependence) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_DEPENDENCE.toString()+" SET Test_Case_Id=?, Dependent_Test_Case_Id=?, Remark=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{basicTestCaseDependence.getBasicTestCase().getId(), basicTestCaseDependence.getBasicDependentTestCase().getId(), basicTestCaseDependence.getRemark(), basicTestCaseDependence.getId()});
    }

    public void delete(BasicTestCaseDependence basicTestCaseDependence) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_DEPENDENCE.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{basicTestCaseDependence.getId()});
    }

    public BasicTestCaseDependence findBasicById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_DEPENDENCE.toString() + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<BasicTestCaseDependence>() {
            public BasicTestCaseDependence mapRow(ResultSet resultSet, int i) throws SQLException {
                BasicTestCaseDependence basicTestCaseDependence = new BasicTestCaseDependence();
                basicTestCaseDependence.setId(resultSet.getInt("Id"));
                basicTestCaseDependence.setBasicTestCase(testCaseDao.findBasicById(resultSet.getInt("Test_Case_Id")));
                basicTestCaseDependence.setBasicDependentTestCase(testCaseDao.findBasicById(resultSet.getInt("Dependent_Test_Case_Id")));
                basicTestCaseDependence.setRemark(resultSet.getString("Remark"));
                return basicTestCaseDependence;
            }
        });
    }
}
