package com.xieziming.stap.core.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionRequestController {
    private static Logger logger = LoggerFactory.getLogger(ExecutionRequestController.class);
    @Autowired
    private ExecutionRequestHandler executionRequestHandler;

    public ExecutionRequestResult handleRequest(ExecutionRequest executionRequest){
        return executionRequestHandler.handle(executionRequest);
    }
}