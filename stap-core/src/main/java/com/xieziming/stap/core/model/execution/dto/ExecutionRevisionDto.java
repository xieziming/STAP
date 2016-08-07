package com.xieziming.stap.core.model.execution.dto;

import com.xieziming.stap.core.model.execution.pojo.ExecutionContextRevision;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlanRevision;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Suny on 8/7/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionRevisionDto {
    private List<ExecutionPlanRevision> executionPlanRevisionList;
    private List<ExecutionContextRevision> executionContextRevisionList;
}
