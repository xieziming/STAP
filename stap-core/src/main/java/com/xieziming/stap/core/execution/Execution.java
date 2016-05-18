package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.execution.step.ExecutionStep;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/8/16.
 */
public class Execution {
    private Integer id;
    private Integer testCaseId;
    private Integer executionPlanId;
    private Integer executionContextId;
    private Date startTime;
    private Date endTime;
    private String status;
    private String remark;
    private List<ExecutionStep> executionStepList;
    private List<ExecutionLog> executionLogList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Integer getExecutionPlanId() {
        return executionPlanId;
    }

    public void setExecutionPlanId(Integer executionPlanId) {
        this.executionPlanId = executionPlanId;
    }

    public Integer getExecutionContextId() {
        return executionContextId;
    }

    public void setExecutionContextId(Integer executionContextId) {
        this.executionContextId = executionContextId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ExecutionStep> getExecutionStepList() {
        return executionStepList;
    }

    public void setExecutionStepList(List<ExecutionStep> executionStepList) {
        this.executionStepList = executionStepList;
    }

    public List<ExecutionLog> getExecutionLogList() {
        return executionLogList;
    }

    public void setExecutionLogList(List<ExecutionLog> executionLogList) {
        this.executionLogList = executionLogList;
    }
}
