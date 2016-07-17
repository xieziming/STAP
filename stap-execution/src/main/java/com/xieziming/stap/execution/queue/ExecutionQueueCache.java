package com.xieziming.stap.execution.queue;

import com.xieziming.stap.core.execution.raw.RawExecution;
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
    private Queue<RawExecution> rawExecutionQueue = new LinkedList<RawExecution>();
    @Autowired
    private ExecutionDao executionDao;
    public Queue<RawExecution> getRawExecutionQueue(){
        if(rawExecutionQueue == null || rawExecutionQueue.size() == 0){
            synchronized (ExecutionQueueCache.class){
                if(rawExecutionQueue == null || rawExecutionQueue.size() == 0){
                    List<RawExecution> rawExecutionList = executionDao.findAllBasic();
                    for (RawExecution rawExecution : rawExecutionList){
                        rawExecutionQueue.offer(rawExecution);
                    }
                }
            }
        }
        return rawExecutionQueue;
    }
}
