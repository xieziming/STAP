package com.xieziming.stap.executor.filter.filters;

import com.xieziming.stap.core.constants.ExecutionResultConstants;
import com.xieziming.stap.core.execution.raw.RawExecution;
import com.xieziming.stap.channel.filter.ExecutionFilter;

/**
 * Created by Suny on 5/22/16.
 */
public class ExecutionResultFilter implements ExecutionFilter {
    @Override
    public boolean shouldBeExecuted(RawExecution execution) {
        if(execution.getResult().equalsIgnoreCase(ExecutionResultConstants.PASS.toString())) return false;
        return true;
    }
}
