package com.xieziming.stap.execution.queue;

import com.xieziming.stap.core.execution.BasicExecution;
import com.xieziming.stap.dao.execution.ExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Suny on 5/20/16.
 */
@Component
public class ExecutionQueueCache {
    private Queue<BasicExecution> basicExecutionQueue = new LinkedList<BasicExecution>();
    @Autowired
    private ExecutionDao executionDao;
    public Queue<BasicExecution> getBasicExecutionQueue(){
        if(basicExecutionQueue == null || basicExecutionQueue.size() == 0){
            synchronized (ExecutionQueueCache.class){
                if(basicExecutionQueue == null || basicExecutionQueue.size() == 0){
                    List<BasicExecution> basicExecutionList = executionDao.findAllBasic();
                    for (BasicExecution basicExecution : basicExecutionList){
                        basicExecutionQueue.offer(basicExecution);
                    }
                }
            }
        }
        return basicExecutionQueue;
    }
}
