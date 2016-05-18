package com.xieziming.stap.core.execution.output;

import com.xieziming.stap.core.common.StapFile;

/**
 * Created by Suny on 5/9/16.
 */
public final class ExecutionOutputFile extends ExecutionOutput{
    private StapFile stapFile;

    public StapFile getStapFile() {
        return stapFile;
    }

    public void setStapFile(StapFile stapFile) {
        this.stapFile = stapFile;
    }
}
