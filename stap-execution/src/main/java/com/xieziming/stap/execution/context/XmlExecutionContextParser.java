package com.xieziming.stap.execution.context;

import com.xieziming.stap.core.execution.BasicExecution;
import com.xieziming.stap.core.execution.ExecutionContext;
import com.xieziming.stap.execution.filter.ExecutionFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
@Component
public class XmlExecutionContextParser implements ExecutionContextParser{
    private ExecutionContext executionContext;

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public void setExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    @Override
    public List<ExecutionFilter> getExecutionFilterList() {
        List<ExecutionFilter> executionFilterList = new ArrayList<ExecutionFilter>();
        ExecutionFilter executionFilter = new ExecutionFilter() {
            @Override
            public boolean shouldBeExecuted(BasicExecution execution) {
                return true;
            }
        };
        executionFilterList.add(executionFilter);
        return executionFilterList;
    }
}
