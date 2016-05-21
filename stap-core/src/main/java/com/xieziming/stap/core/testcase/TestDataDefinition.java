package com.xieziming.stap.core.testcase;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.file.StapFile;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Suny on 5/21/16.
 */
public class TestDataDefinition {
    private Integer id;
    private String field;
    private String value;
    private StapFile stapFile;
    private String remark;
    private String type;
    @JsonSerialize(using = JsonDateSerializer.class)
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

    public StapFile getStapFile() {
        return stapFile;
    }

    public void setStapFile(StapFile stapFile) {
        this.stapFile = stapFile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
