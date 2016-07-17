package com.xieziming.stap.executor.filter.filters;

import com.xieziming.stap.core.constants.ExecutionResultConstants;
import com.xieziming.stap.core.execution.raw.RawExecution;
import com.xieziming.stap.executor.filter.ExecutionFilter;

/**
 * Created by Suny on 5/22/16.
 */
public class ExecutionPlanStatusFilter implements ExecutionFilter {
    @Override
    public boolean shouldBeExecuted(RawExecution execution) {
        if(execution.getBasicExecutionPlan().getStatus().equalsIgnoreCase(ExecutionResultConstants.CLOSED.toString())) return false;
        return true;
    }
}
