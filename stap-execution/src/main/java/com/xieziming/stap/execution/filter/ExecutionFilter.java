package com.xieziming.stap.execution.filter;

import com.xieziming.stap.core.execution.BasicExecution;

/**
 * Created by Suny on 5/21/16.
 */
public interface ExecutionFilter {
    public boolean shouldBeExecuted(BasicExecution execution);
}
