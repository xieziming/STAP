package com.xieziming.stap.core.testcase;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xieziming.stap.core.util.JsonDateSerializer;

import java.util.Date;

/**
 * Created by Suny on 5/22/16.
 */
public class BasicTestCase {
    private Integer id;
    private BasicTestCase basicParentTestCase;
    private String name;
    private String remark;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicTestCase getBasicParentTestCase() {
        return basicParentTestCase;
    }

    public void setBasicParentTestCase(BasicTestCase basicParentTestCase) {
        this.basicParentTestCase = basicParentTestCase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
