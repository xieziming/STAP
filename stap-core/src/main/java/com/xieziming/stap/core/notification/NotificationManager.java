package com.xieziming.stap.core.notification;

import com.xieziming.stap.core.constants.NotificationStatus;
import com.xieziming.stap.core.model.comment.pojo.Comment;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.notification.dao.NotificationDao;
import com.xieziming.stap.core.model.notification.dao.WatchListDao;
import com.xieziming.stap.core.model.notification.pojo.Notification;
import com.xieziming.stap.core.model.notification.pojo.WatchList;
import com.xieziming.stap.core.model.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suny on 8/20/16.
 */
@Component
public class NotificationManager {
    @Autowired
    private NotificationDao notificationDao;
    @Autowired
    private WatchListDao watchListDao;
    @Autowired
    private UserDao userDao;

    private List<WatchList> watchLists;
    private

    public NotificationManager withExecutionId(int executionId) {
        watchLists = watchListDao.findAllByExecutionId(executionId);
        return this;
    }

    public NotificationManager withExecutionPlanId(int executionPlanId) {
        watchLists = watchListDao.findAllByExecutionPlanId(executionPlanId);
        return this;
    }

    public NotificationManager withTestCaseId(int testCaseId) {
        watchLists = watchListDao.findAllByTestCaseId(testCaseId);
        return this;
    }

    public void notify(String content){
        for(WatchList watchList : watchLists) {
            Notification notification = new Notification();
            notification.setWatchListId(watchList.getId());
            notification.setStatus(NotificationStatus.PRODUCED);
            notification.setContent(content);
            notificationDao.add(notification);
        }
    }
}
