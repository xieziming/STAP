package com.xieziming.stap.execution.context;

import com.xieziming.stap.core.execution.ExecutionContext;
import com.xieziming.stap.execution.filters.ExecutionFilter;

import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
public class JsonExecutionContextParser extends ExecutionContextParser{

    public JsonExecutionContextParser(ExecutionContext executionContext) {
        super(executionContext);
    }

    @Override
    public List<ExecutionFilter> getExecutionFilterList() {
        return null;
    }
}
