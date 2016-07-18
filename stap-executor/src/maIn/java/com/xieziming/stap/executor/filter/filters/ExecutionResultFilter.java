package com.xieziming.stap.executor.filter.filters;

import com.xieziming.stap.core.constants.ExecutionResultType;
import com.xieziming.stap.core.execution.pojo.Execution;
import com.xieziming.stap.channel.filter.ExecutionFilter;

/**
 * Created by Suny on 5/22/16.
 */
public class ExecutionResultFilter implements ExecutionFilter {
    @Override
    public boolean shouldBeExecuted(Execution execution) {
        if(execution.getResult().equalsIgnoreCase(ExecutionResultType.PASS.toString())) return false;
        return true;
    }
}
