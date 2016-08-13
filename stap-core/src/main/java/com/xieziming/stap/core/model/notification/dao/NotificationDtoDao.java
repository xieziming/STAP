package com.xieziming.stap.core.model.notification.dao;

import com.xieziming.stap.core.model.notification.converter.NotificationConverter;
import com.xieziming.stap.core.model.notification.dto.NotificationDto;
import com.xieziming.stap.core.model.notification.pojo.Notification;
import com.xieziming.stap.core.model.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suny on 8/12/16.
 */
@Component
public class NotificationDtoDao {
    private static Logger logger = LoggerFactory.getLogger(NotificationDtoDao.class);

    @Autowired
    private NotificationDao notificationDao;
    @Autowired
    private NotificationConverter notificationConverter;
    @Autowired
    private UserDao userDao;

    public NotificationDto findById(int id) {
        return notificationConverter.convert(notificationDao.findById(id));
    }

    public List<NotificationDto> findAllByWatchListId(int watchListId) {
        return notificationConverter.convertAll(notificationDao.findAllByWatchListId(watchListId));
    }

    public List<NotificationDto> findAllByUserName(String userName) {
        return findAllByUserId(userDao.findByName(userName).getId());
    }

    public List<NotificationDto> findAllByUserId(int userId) {
        List<Notification> watchLists= notificationDao.findAllByUserId(userId);
        return notificationConverter.convertAll(watchLists);
    }
}
