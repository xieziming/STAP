package com.xieziming.stap.core.model.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 8/12/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchListDto {
    private Integer id;
    private String source;
    private Integer sourceId;
    private String sourceName;
    private Integer userId;
}
