package com.xieziming.stap.core.execution.output;

/**
 * Created by Suny on 5/9/16.
 */
public final class ExecutionOutputText extends ExecutionOutput {
    private String field;
    private String value;

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
}
