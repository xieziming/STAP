package com.xieziming.stap.core.model.notification.dao;

import com.xieziming.stap.core.model.notification.pojo.WatchList;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 8/12/16.
 */
@Component
public class WatchListDao {
    private static Logger logger = LoggerFactory.getLogger(WatchListDao.class);

    public WatchList findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST+ " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, watchListDtoRowMapper);
    }

    public List<WatchList> findAllByExecutionPlanId(int executionPlanId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE Execution_Plan_Id=? ";
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId}, watchListDtoRowMapper);
    }
    public List<WatchList> findAllByExecutionId(int executionId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE Execution_Id=? ";
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId}, watchListDtoRowMapper);
    }

    public List<WatchList> findAllByTestCaseId(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE Test_Case_Id=? ";
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, watchListDtoRowMapper);
    }

    public List<WatchList> findAllByUser(int userId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE User_Id=?";
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[]{userId}, watchListDtoRowMapper);
    }

    public void add(WatchList watchList) {
        String sql = "INSERT INTO "+ StapDbTables.WATCH_LIST+" SET Test_Case_Id=?, Execution_Plan_Id=?, Execution_Id=?, User_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{watchList.getTestCaseId(), watchList.getExecutionPlanId(), watchList.getExecutionId(), watchList.getUserId()});
    }

    public void update(WatchList watchList) {
        String sql = "UPDATE "+StapDbTables.WATCH_LIST+" SET Test_Case_Id=?, Execution_Plan_Id=?, Test_Case_Id=?, User_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{watchList.getTestCaseId(), watchList.getExecutionPlanId(), watchList.getExecutionId(), watchList.getUserId(), watchList.getId()});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.WATCH_LIST+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAllByExecutionPlanId(int executionPlanId) {
        String sql = "DELETE FROM "+StapDbTables.WATCH_LIST+" WHERE Execution_Plan_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    public void deleteAllByExecutionId(int executionId) {
        String sql = "DELETE FROM "+StapDbTables.WATCH_LIST+" WHERE Execution_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }

    public void deleteAllByTestCaseId(int testCaseId) {
        String sql = "DELETE FROM "+StapDbTables.WATCH_LIST+" WHERE Test_Case_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseId});
    }

    private RowMapper<WatchList> watchListDtoRowMapper = new RowMapper<WatchList>() {
        public WatchList mapRow(ResultSet resultSet, int i) throws SQLException {
            WatchList watchList = new WatchList();
            watchList.setId(resultSet.getInt("Id"));
            watchList.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            watchList.setExecutionPlanId(resultSet.getInt("Execution_Plan_Id"));
            watchList.setExecutionId(resultSet.getInt("Execution_Id"));
            watchList.setUserId(resultSet.getInt("User_Id"));
            return watchList;
        }
    };
}
