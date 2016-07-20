package com.xieziming.stap.core.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionController {
    private static Logger log = LoggerFactory.getLogger(ExecutionController.class);
    @Autowired
    private ExecutionRequestHandler executionRequestHandler;

    public ExecutionResponse request(ExecutionRequest executionRequest){
        log.info("{} request to run execution {}", executionRequest.getRequester()+"("+executionRequest.getFromIp()+")", executionRequest.getExecutionId());
        return executionRequestHandler.handle(executionRequest);
    }
}