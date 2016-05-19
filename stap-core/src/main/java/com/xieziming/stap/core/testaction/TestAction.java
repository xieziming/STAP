package com.xieziming.stap.core.testaction;

/**
 * Created by Suny on 5/8/16.
 */
public class TestAction {
    private Integer id;
    private String name;
    private TestActionHandler testActionHandler;
    private String remark;

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

    public TestActionHandler getTestActionHandler() {
        return testActionHandler;
    }

    public void setTestActionHandler(TestActionHandler testActionHandler) {
        this.testActionHandler = testActionHandler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
