package com.xieziming.stap.core.execution.step;

import com.xieziming.stap.core.common.StapLog;
import com.xieziming.stap.core.execution.ExecutionContext;
import com.xieziming.stap.core.execution.output.ExecutionOutput;
import com.xieziming.stap.core.testcase.TestCase;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
public class ExecutionStep {
    private Integer id;
    private Integer executionId;
    private Integer testStepId;
    private Date startTime;
    private Date endTime;
    private String status;
    private String result;
    private String remark;
    private List<ExecutionOutput> executionOutputList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExecutionId() {
        return executionId;
    }

    public void setExecutionId(Integer executionId) {
        this.executionId = executionId;
    }

    public Integer getTestStepId() {
        return testStepId;
    }

    public void setTestStepId(Integer testStepId) {
        this.testStepId = testStepId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ExecutionOutput> getExecutionOutputList() {
        return executionOutputList;
    }

    public void setExecutionOutputList(List<ExecutionOutput> executionOutputList) {
        this.executionOutputList = executionOutputList;
    }
}
