package com.xieziming.stap.execution.context;

import com.xieziming.stap.core.execution.ExecutionContext;
import com.xieziming.stap.execution.filters.ExecutionFilter;

import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
public abstract class ExecutionContextParser {
    private ExecutionContext executionContext;

    public ExecutionContextParser(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public abstract List<ExecutionFilter> getExecutionFilterList();

    public static ExecutionContextParser newInstance(String className, ExecutionContext executionContext) throws Exception {
        return (ExecutionContextParser) Class.forName(className).getConstructor(ExecutionContext.class).newInstance(executionContext);
    }
}
