package com.xieziming.stap.execution.queue;

import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
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
    private Queue<Execution> executionQueue = new LinkedList<Execution>();
    @Autowired
    private ExecutionDao executionDao;
    public Queue<Execution> getExecutionQueue(){
        if(executionQueue == null || executionQueue.size() == 0){
            synchronized (ExecutionQueueCache.class){
                if(executionQueue == null || executionQueue.size() == 0){
                    List<Execution> executionList = executionDao.findAllBasic();
                    for (Execution execution : executionList){
                        executionQueue.offer(execution);
                    }
                }
            }
        }
        return executionQueue;
    }
}
