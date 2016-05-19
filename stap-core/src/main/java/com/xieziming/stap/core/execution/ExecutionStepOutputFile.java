package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.file.StapFile;

import java.util.Date;

/**
 * Created by Suny on 5/9/16.
 */
public final class ExecutionStepOutputFile {
    private Integer id;
    private ExecutionStep executionStep;
    private String type;
    private String remark;
    private StapFile stapFile;
    private Date lastUpDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExecutionStep getExecutionStep() {
        return executionStep;
    }

    public void setExecutionStep(ExecutionStep executionStep) {
        this.executionStep = executionStep;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public StapFile getStapFile() {
        return stapFile;
    }

    public void setStapFile(StapFile stapFile) {
        this.stapFile = stapFile;
    }

    public Date getLastUpDate() {
        return lastUpDate;
    }

    public void setLastUpDate(Date lastUpDate) {
        this.lastUpDate = lastUpDate;
    }
}
