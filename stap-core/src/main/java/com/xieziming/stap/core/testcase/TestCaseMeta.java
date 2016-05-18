package com.xieziming.stap.core.testcase;

import com.xieziming.stap.core.common.StapMeta;

/**
 * Created by Suny on 5/9/16.
 */
public class TestCaseMeta {
    private Integer id;
    private Integer testCaseId;
    private StapMeta stapMeta;

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

    public StapMeta getStapMeta() {
        return stapMeta;
    }

    public void setStapMeta(StapMeta stapMeta) {
        this.stapMeta = stapMeta;
    }
}
