package com.xieziming.stap.core.model.notification.dao;

import com.xieziming.stap.core.model.notification.pojo.Notification;
import com.xieziming.stap.core.model.notification.pojo.WatchList;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/12/16.
 */
@Component
public class NotificationDao {
    private static Logger logger = LoggerFactory.getLogger(NotificationDao.class);
    @Autowired
    private WatchListDao watchListDao;

    public Notification findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.NOTIFICATION+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, notificationDtoRowMapper);
    }

    public List<Notification> findAllByWatchListId(int watchListId) {
        String sql = "SELECT * FROM " + StapDbTables.NOTIFICATION+ " WHERE Watch_List_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{watchListId}, notificationDtoRowMapper);
    }

    public List<Notification> findAllByUserId(int userId) {
        List<Notification> notificationList = new ArrayList<Notification>();
        List<WatchList> watchLists = watchListDao.findAllByUser(userId);
        for(WatchList watchList : watchLists){
            notificationList.addAll(findAllByWatchListId(watchList.getId()));
        }
        return notificationList;
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

    private RowMapper<Notification> notificationDtoRowMapper = new RowMapper<Notification>() {
        public Notification mapRow(ResultSet resultSet, int i) throws SQLException {
            Notification notification = new Notification();
            notification.setWatchListId(resultSet.getInt("Watch_List_Id"));
            notification.setContent(resultSet.getString("Content"));
            notification.setStatus(resultSet.getString("Status"));
            notification.setTime(resultSet.getTimestamp("Time"));
            return notification;
        }
    };
}
