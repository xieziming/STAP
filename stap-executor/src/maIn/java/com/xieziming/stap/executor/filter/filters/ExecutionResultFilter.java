package com.xieziming.stap.executor.filter.filters;

import com.xieziming.stap.core.common.ExecutionConstants;
import com.xieziming.stap.core.execution.BasicExecution;
import com.xieziming.stap.channel.filter.ExecutionFilter;

/**
 * Created by Suny on 5/22/16.
 */
public class ExecutionResultFilter implements ExecutionFilter {
    @Override
    public boolean shouldBeExecuted(BasicExecution execution) {
        if(execution.getResult().equalsIgnoreCase(ExecutionConstants.PASS.toString())) return false;
        return true;
    }
}
