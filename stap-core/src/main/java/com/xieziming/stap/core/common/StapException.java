package com.xieziming.stap.core.common;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Created by Suny on 5/18/16.
 */
public class StapException extends RuntimeException{
    private String errCode;
    private String errMsg;
    private String trace;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public StapException(String errCode, String errMsg, String trace) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.trace = trace;
    }

    public StapException(String errCode, String errMsg) {
        this(errCode, errMsg, null);
    }

    public StapException(Exception exception) {
        this("Unknown Exception", exception.getMessage(), ExceptionUtils.getStackTrace(exception));
    }
}
