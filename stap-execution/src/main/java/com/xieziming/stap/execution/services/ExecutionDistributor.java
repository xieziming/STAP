package com.xieziming.stap.execution.services;

import com.xieziming.stap.core.execution.BasicExecution;
import com.xieziming.stap.core.execution.Execution;
import com.xieziming.stap.execution.queue.ExecutionQueue;
import com.xieziming.stap.execution.queue.ExecutionQueueCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Queue;

/**
 * Created by Suny on 5/19/16.
 */
@Controller
public class ExecutionDistributor {
    private static Logger logger = LoggerFactory.getLogger(ExecutionDistributor.class);
    private final String UTF8 = ";charset=UTF-8";

    @Value("${execution.context.parser}")
    private String executionContextParser;

    @Autowired
    private ExecutionQueueCache executionQueueCache;

    @Autowired
    private ExecutionQueue executionQueue;

    @RequestMapping(value = "executions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public Queue<BasicExecution> getExecutions() {
        Queue<BasicExecution> basicExecutionList = executionQueueCache.getBasicExecutionQueue();
        return basicExecutionList;
    }

    @RequestMapping(value = "execution/request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public Execution requestExecution() {
        return executionQueue.getExecution();
    }
}
