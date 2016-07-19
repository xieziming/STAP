package com.xieziming.stap.executor.filter.filters;

import com.xieziming.stap.core.constants.ExecutionStatusType;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.executor.filter.ExecutionFilter;

/**
 * Created by Suny on 5/22/16.
 */
public class ExecutionPlanStatusFilter implements ExecutionFilter {

    public boolean shouldBeExecuted(Execution execution) {
        if(execution.getStatus().equalsIgnoreCase(ExecutionStatusType.COMPLETED)) return false;
        return true;
    }
}
