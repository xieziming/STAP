package com.xieziming.stap.core.testcase;

import com.xieziming.stap.core.common.StapFile;

import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
public class TestData {
    private Integer id;
    private String field;
    private String value;
    private StapFile fileReference;
    private String remark;
    private Date lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StapFile getFileReference() {
        return fileReference;
    }

    public void setFileReference(StapFile fileReference) {
        this.fileReference = fileReference;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
