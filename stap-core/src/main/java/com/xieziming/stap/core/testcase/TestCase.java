package com.xieziming.stap.core.testcase;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/8/16.
 */
public class TestCase {
    private Integer id;
    private String name;
    private Integer parentTestCaseId;
    private String remark;
    private List<TestCaseMeta> testCaseMetaList;
    private List<TestData> testDataList;
    private List<TestStep> testStepList;
    private Date lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentTestCaseId() {
        return parentTestCaseId;
    }

    public void setParentTestCaseId(Integer parentTestCaseId) {
        this.parentTestCaseId = parentTestCaseId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TestCaseMeta> getTestCaseMetaList() {
        return testCaseMetaList;
    }

    public void setTestCaseMetaList(List<TestCaseMeta> testCaseMetaList) {
        this.testCaseMetaList = testCaseMetaList;
    }

    public List<TestData> getTestDataList() {
        return testDataList;
    }

    public void setTestDataList(List<TestData> testDataList) {
        this.testDataList = testDataList;
    }

    public List<TestStep> getTestStepList() {
        return testStepList;
    }

    public void setTestStepList(List<TestStep> testStepList) {
        this.testStepList = testStepList;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
