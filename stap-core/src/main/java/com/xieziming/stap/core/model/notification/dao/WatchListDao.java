package com.xieziming.stap.core.model.notification.dao;

import com.xieziming.stap.core.model.notification.dto.WatchListDto;
import com.xieziming.stap.core.model.notification.pojo.WatchList;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 8/12/16.
 */
public class WatchListDao {
    private static Logger logger = LoggerFactory.getLogger(WatchListDao.class);

    public WatchListDto findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, watchListDtoRowMapper);
    }

    public List<WatchListDto> findAllByUserAndExecutionPlanId(int executionPlanId, String userId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE Execution_Plan_Id=? AND User_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId, userId}, watchListDtoRowMapper);
    }
    public List<WatchListDto> findAllByUserAndExecutionId(int executionId, String userId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE Execution_Id=? AND User_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId, userId}, watchListDtoRowMapper);
    }

    public List<WatchListDto> findAllUserAndByTestCaseId(int testCaseId, String userId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE Test_Case_Id=? AND User_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId, userId}, watchListDtoRowMapper);
    }

    public List<WatchListDto> findAllByUser(String userId) {
        String sql = "SELECT * FROM " + StapDbTables.WATCH_LIST + " WHERE User_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{userId}, watchListDtoRowMapper);
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

    private RowMapper<WatchListDto> watchListDtoRowMapper = new RowMapper<WatchListDto>() {
        public WatchListDto mapRow(ResultSet resultSet, int i) throws SQLException {
            WatchListDto watchListDto = new WatchListDto();
            watchListDto.setId(resultSet.getInt("Id"));
            watchListDto.setUserId(resultSet.getInt("User_Id"));
            return watchListDto;
        }
    };
}
