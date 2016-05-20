package com.xieziming.stap.execution.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
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
    public boolean shouldBeExecuted() {
        for(ExecutionFilter executionFilter : executionFilterList){
            if(executionFilter.shouldBeExecuted()) return true;
        }
        return false;
    }
}
