package com.xieziming.stap.core.execution.pojo;

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
public class ExecutionOutputFile {
    private Integer id;
    private Integer executionId;
    private Integer executionStepId;
    private String type;
    private Integer fileId;
    private String remark;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;
}
