package com.xieziming.stap.core.model.notification.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 8/12/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchList {
    private Integer id;
    private Integer testCaseId;
    private Integer executionPlanId;
    private Integer executionId;
    private Integer userId;
}
