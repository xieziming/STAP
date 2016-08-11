package com.xieziming.stap.core.model.notification.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 8/12/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private Integer id;
    private Integer watchListId;
    private String content;
    private String status;
    private Date time;
}
