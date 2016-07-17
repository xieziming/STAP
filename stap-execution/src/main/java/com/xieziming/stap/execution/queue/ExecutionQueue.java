package com.xieziming.stap.execution.queue;

import com.xieziming.stap.core.execution.raw.RawExecution;
import com.xieziming.stap.core.execution.Execution;
import com.xieziming.stap.dao.execution.ExecutionDao;
import com.xieziming.stap.channel.context.XmlExecutionContextParser;
import com.xieziming.stap.channel.filter.ExecutionFilter;
import com.xieziming.stap.channel.filter.ExecutionFilterManager;
import com.xieziming.stap.channel.filter.filters.ExecutionPlanStatusFilter;
import com.xieziming.stap.channel.filter.filters.ExecutionResultFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;

/**
 * Created by Suny on 5/20/16.
 */
@Component
public class ExecutionQueue {
    @Autowired
    private ExecutionFilterManager executionFilterManager;

    @Autowired
    private ExecutionQueueCache executionQueueCache;

    @Autowired
    private XmlExecutionContextParser xmlExecutionContextParser;

    @Autowired
    private ExecutionDao executionDao;

    private static Logger logger = LoggerFactory.getLogger(ExecutionQueue.class);

    public Execution getExecution(){
        Queue<RawExecution> executionQueue = executionQueueCache.getRawExecutionQueue();
        RawExecution rawExecution;
        while((rawExecution = executionQueue.poll()) != null){
            try {
                xmlExecutionContextParser.setExecutionContext(rawExecution.getExecutionContext());
                List<ExecutionFilter> executionFilterList = xmlExecutionContextParser.getExecutionFilterList();
                executionFilterManager.addFilter(new ExecutionPlanStatusFilter());
                executionFilterManager.addFilter(new ExecutionResultFilter());
                executionFilterManager.addFilterList(executionFilterList);
                if(executionFilterManager.shouldBeExecuted(rawExecution)){
                    break;
                }
            } catch (Exception e) {
                logger.error("Execution Distribute Error", e);
            }finally {
                executionFilterManager.removeAllFilters();
            }
        }

        if(rawExecution != null){
            logger.info("Execution Delivered <Id:{}, Plan:{}, Test Case:{}>", rawExecution.getId(), rawExecution.getBasicExecutionPlan().getName(), rawExecution.getBasicTestCase().getName());
            return executionDao.findById(rawExecution.getId());
        }

        return null;
    }
}
