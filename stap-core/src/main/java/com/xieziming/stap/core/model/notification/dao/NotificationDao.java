package com.xieziming.stap.core.model.notification.dao;

import com.xieziming.stap.core.model.notification.dto.NotificationDto;
import com.xieziming.stap.core.model.notification.dto.WatchListDto;
import com.xieziming.stap.core.model.notification.pojo.Notification;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/12/16.
 */
public class NotificationDao {
    private static Logger logger = LoggerFactory.getLogger(NotificationDao.class);

    @Autowired
    private WatchListDao watchListDao;

    public NotificationDto findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.NOTIFICATION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, notificationDtoRowMapper);
    }

    public List<NotificationDto> findAllByWatchListId(int watchListId) {
        String sql = "SELECT * FROM " + StapDbTables.NOTIFICATION+ " WHERE Watch_List_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{watchListId}, notificationDtoRowMapper);
    }

    private List<NotificationDto> findAllByWatchList(List<WatchListDto> watchListDtoList){
        List<NotificationDto> notificationDtoList = new ArrayList<NotificationDto>();

        for(WatchListDto watchListDto: watchListDtoList){
            notificationDtoList.addAll(findAllByWatchListId(watchListDto.getId()));
        }
        return notificationDtoList;
    }

    public List<NotificationDto> findAllByExecutionPlanId(int executionPlanId) {
        return findAllByWatchList(watchListDao.findAllByUserAndExecutionPlanId(executionPlanId));
    }

    public List<NotificationDto> findAllByExecutionId(int executionId) {
        return findAllByWatchList(watchListDao.findAllByUserAndExecutionId(executionId));
    }

    public List<NotificationDto> findAllByTestCaseId(int testCaseId) {
        return findAllByWatchList(watchListDao.findAllUserAndByTestCaseId(testCaseId));
    }

    public void add(Notification notification) {
        String sql = "INSERT INTO "+ StapDbTables.NOTIFICATION+" SET Watch_List_Id=?,  Content=?, Status=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{notification.getWatchListId(), notification.getContent(), notification.getStatus()});
    }

    public void update(Notification notification) {
        String sql = "UPDATE "+StapDbTables.NOTIFICATION+" SET Watch_List_Id=?,  Content=?, Status=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{notification.getWatchListId(), notification.getContent(), notification.getStatus(), notification.getId()});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.NOTIFICATION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAllByExecutionPlanId(int executionPlanId) {
        String sql = "DELETE FROM "+StapDbTables.NOTIFICATION+" WHERE Execution_Plan_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    public void deleteAllByExecutionId(int executionId) {
        String sql = "DELETE FROM "+StapDbTables.NOTIFICATION+" WHERE Execution_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }

    public void deleteAllByTestCaseId(int testCaseId) {
        String sql = "DELETE FROM "+StapDbTables.NOTIFICATION+" WHERE Test_Case_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseId});
    }

    private RowMapper<NotificationDto> notificationDtoRowMapper = new RowMapper<NotificationDto>() {
        public NotificationDto mapRow(ResultSet resultSet, int i) throws SQLException {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setId(resultSet.getInt("Id"));
            notificationDto.setContent(resultSet.getString("Content"));
            notificationDto.setStatus(resultSet.getString("Status"));
            notificationDto.setTime(resultSet.getTimestamp("Time"));
            return notificationDto;
        }
    };
}
