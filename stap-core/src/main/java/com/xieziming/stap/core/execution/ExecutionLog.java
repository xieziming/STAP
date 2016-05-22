package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.common.StapLog;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionLog {
    private Integer id;
    private BasicExecution basicExecution;
    private StapLog stapLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicExecution getBasicExecution() {
        return basicExecution;
    }

    public void setBasicExecution(BasicExecution basicExecution) {
        this.basicExecution = basicExecution;
    }

    public StapLog getStapLog() {
        return stapLog;
    }

    public void setStapLog(StapLog stapLog) {
        this.stapLog = stapLog;
    }
}
