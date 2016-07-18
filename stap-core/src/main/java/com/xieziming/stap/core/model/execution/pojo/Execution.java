package com.xieziming.stap.core.model.execution.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Execution {
    private Integer id;
    private Integer executionPlanId;
    private Integer testCaseId;
    private Integer executionContextId;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date startTime;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date endTime;
    private String status;
    private String result;
    private String remark;
}
