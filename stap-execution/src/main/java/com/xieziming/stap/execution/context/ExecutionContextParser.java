package com.xieziming.stap.execution.context;

import com.xieziming.stap.execution.filter.ExecutionFilter;

import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
public interface ExecutionContextParser {
    List<ExecutionFilter> getExecutionFilterList();
}
