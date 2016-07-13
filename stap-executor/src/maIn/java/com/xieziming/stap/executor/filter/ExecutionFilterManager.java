package com.xieziming.stap.executor.filter;

import com.xieziming.stap.core.execution.BasicExecution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
@Component
public class ExecutionFilterManager implements ExecutionFilter{
    private List<ExecutionFilter> executionFilterList = new ArrayList<ExecutionFilter>();

    public void addFilter(ExecutionFilter executionFilter) {
        this.executionFilterList.add(executionFilter);
    }

    public void addFilterList(List<ExecutionFilter> executionFilterList){
        this.executionFilterList.addAll(executionFilterList);
    }

    public void removeAllFilters(){
        this.executionFilterList.clear();
    }

    @Override
    public boolean shouldBeExecuted(BasicExecution execution) {
        for(ExecutionFilter executionFilter : executionFilterList){
            if(executionFilter.shouldBeExecuted(execution)) return true;
        }
        return false;
    }
}
