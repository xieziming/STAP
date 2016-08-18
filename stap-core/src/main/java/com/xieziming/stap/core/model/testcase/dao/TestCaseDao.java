package com.xieziming.stap.core.model.testcase.dao;

import com.xieziming.stap.core.model.comment.dao.CommentDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.notification.dao.WatchListDao;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class TestCaseDao {
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;
    @Autowired
    private TestCaseRelationDao testCaseRelationDao;
    @Autowired
    private TestStepDao testStepDao;
    @Autowired
    private TestDataDao testDataDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private WatchListDao watchListDao;
    @Autowired
    private TestCaseRevisionDao testCaseRevisionDao;

    public void add(TestCase testCase) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE+" SET Name=?, Status=?, Description=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getStatus(), testCase.getDescription()});
    }

    public void update(TestCase testCase) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE+" SET Name=?, Status=?, Description=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCase.getName(), testCase.getStatus(), testCase.getDescription(), testCase.getId()});
    }

    public void deleteAll(){
        List<TestCase> testCaseList = findAll();
        for(TestCase testCase : testCaseList){
            delete(testCase.getId());
        }
    }

    public void delete(int id) {
        commentDao.deleteAllByTestCaseId(id);
        watchListDao.deleteAllByTestCaseId(id);
        testStepDao.deleteAllByTestCaseId(id);
        testCaseMetaDao.deleteByTestCaseId(id);
        testDataDao.deleteAllByTestCaseId(id);
        testCaseRelationDao.deleteAllByTestCaseId(id);
        executionDao.deleteAllByTestCaseId(id);
        testCaseRevisionDao.deleteAll(id);

        String sql = "DELETE FROM "+StapDbTables.TEST_CASE+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public List<TestCase> findAll() {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE;
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[0], testCaseRowMapper);
    }

    public TestCase findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testCaseRowMapper);
    }

    public TestCase findByName(String name) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE + " WHERE Name=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{name}, testCaseRowMapper);
    }

    RowMapper<TestCase> testCaseRowMapper = new RowMapper<TestCase>() {
        public TestCase mapRow(ResultSet resultSet, int i) throws SQLException {
            TestCase testCase = new TestCase();
            testCase.setId(resultSet.getInt("Id"));
            testCase.setName(resultSet.getString("Name"));
            testCase.setDescription(resultSet.getString("Description"));
            testCase.setStatus(resultSet.getString("Status"));
            testCase.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return testCase;
        }
    };
}
