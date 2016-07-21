package com.xieziming.stap.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Created by Suny on 5/18/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StapException extends RuntimeException{
    private String errCode;
    private String errMsg;
    private String trace;

    public StapException(Exception exception) {
        this("Unknown Exception", exception.getMessage(), ExceptionUtils.getStackTrace(exception));
    }

    public StapException(String errCode, String errMsg){
        this(errCode, errMsg, null);
    }

    public StapException(String errMsg){
        this(null, errMsg, null);
    }
}
