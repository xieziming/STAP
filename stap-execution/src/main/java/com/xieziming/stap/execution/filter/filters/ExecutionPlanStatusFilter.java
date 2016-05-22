package com.xieziming.stap.execution.filter.filters;

import com.xieziming.stap.core.common.ExecutionConstants;
import com.xieziming.stap.core.execution.BasicExecution;
import com.xieziming.stap.execution.filter.ExecutionFilter;

/**
 * Created by Suny on 5/22/16.
 */
public class ExecutionPlanStatusFilter implements ExecutionFilter {
    @Override
    public boolean shouldBeExecuted(BasicExecution execution) {
        if(execution.getBasicExecutionPlan().getStatus().equalsIgnoreCase(ExecutionConstants.CLOSED.toString())) return false;
        return true;
    }
}
