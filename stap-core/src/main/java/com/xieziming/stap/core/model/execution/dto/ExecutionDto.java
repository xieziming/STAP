package com.xieziming.stap.core.model.execution.dto;

import com.xieziming.stap.core.model.execution.pojo.Execution;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 7/19/16.
 */
@Data
@NoArgsConstructor
public class ExecutionDto extends Execution{
    private String executionPlanName;
    private String executionContextName;
    private String testCaseName;
}
