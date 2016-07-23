package com.xieziming.stap.core.model.execution.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 7/19/16.
 */
@Data
@NoArgsConstructor
public class ExecutionBriefDto {
    private Integer id;
    private Integer executionPlanId;
    private String executionPlanName;
    private Integer executionContextId;
    private String executionContextName;
    private Integer testCaseId;
    private String testCaseName;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date startTime;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date endTime;
    private String status;
    private String result;
    private String remark;
}
