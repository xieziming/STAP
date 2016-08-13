package com.xieziming.stap.core.model.notification.converter;
import com.xieziming.stap.core.model.notification.dao.WatchListDao;
import com.xieziming.stap.core.model.notification.dto.NotificationDto;
import com.xieziming.stap.core.model.notification.pojo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/13/16.
 */
@Component
public class NotificationConverter {
    @Autowired
    private WatchListDao watchListDao;
    @Autowired
    private WatchListConverter watchListConverter;

    public List<NotificationDto> convertAll(List<Notification> notifications){
        List<NotificationDto> notificationDtoList = new ArrayList<NotificationDto>();
        for (Notification notification : notifications){
            notificationDtoList.add(convert(notification));
        }
        return notificationDtoList;
    }

    public NotificationDto convert(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setWatchListDto(watchListConverter.convert(watchListDao.findById(notification.getWatchListId())));
        notificationDto.setContent(notification.getContent());
        notificationDto.setStatus(notification.getStatus());
        notificationDto.setTime(notification.getTime());
        return notificationDto;
    }
}
