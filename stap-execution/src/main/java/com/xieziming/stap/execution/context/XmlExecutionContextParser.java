package com.xieziming.stap.execution.context;

import com.xieziming.stap.core.execution.ExecutionContext;
import com.xieziming.stap.execution.filters.ExecutionFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
public class XmlExecutionContextParser extends ExecutionContextParser {

    public XmlExecutionContextParser(ExecutionContext executionContext) {
        super(executionContext);
    }

    @Override
    public List<ExecutionFilter> getExecutionFilterList() {
        List<ExecutionFilter> executionFilterList = new ArrayList<ExecutionFilter>();
        ExecutionFilter executionFilter = new ExecutionFilter() {
            @Override
            public boolean shouldBeExecuted() {
                return true;
            }
        };
        executionFilterList.add(executionFilter);
        return executionFilterList;
    }
}
