package com.xieziming.stap.execution.queue;

import com.xieziming.stap.core.execution.Execution;
import com.xieziming.stap.execution.context.ExecutionContextParser;
import com.xieziming.stap.execution.filters.ExecutionFilter;
import com.xieziming.stap.execution.filters.ExecutionFilterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;

/**
 * Created by Suny on 5/20/16.
 */
public class ExecutionQueue {
    private ExecutionFilterManager executionFilterManager = new ExecutionFilterManager();
    private static Logger logger = LoggerFactory.getLogger(ExecutionQueue.class);

    public Execution getExecution(String executionContextParserClassName){
        Queue<Execution> executionQueue = ExecutionQueueCache.getExecutionQueue();
        Execution execution;
        while((execution = executionQueue.poll()) != null){
            try {
                ExecutionContextParser  executionContextParser = ExecutionContextParser.newInstance(executionContextParserClassName, execution.getExecutionContext());
                List<ExecutionFilter> executionFilterList = executionContextParser.getExecutionFilterList();
                executionFilterManager.addFilterList(executionFilterList);
                if(executionFilterManager.shouldBeExecuted()){
                    logger.info("Execution Delivered <Id:{}, Plan:{}, Test Case:{}>", execution.getId(), execution.getExecutionPlan().getName(), execution.getTestCase().getName());
                    return execution;
                }
            } catch (Exception e) {
                logger.error("Execution Distribute Error", e);
            }finally {
                executionFilterManager.removeAllFilters();
            }
        }
        return null;
    }
}
