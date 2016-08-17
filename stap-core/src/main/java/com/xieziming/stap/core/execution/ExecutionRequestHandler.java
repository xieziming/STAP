package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.constants.ExecutionStatusType;
import com.xieziming.stap.core.constants.LogLevel;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.converter.ExecutionConverter;
import com.xieziming.stap.core.model.execution.dao.ExecutionLogDao;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.execution.pojo.ExecutionLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionRequestHandler {
    private static Logger logger = LoggerFactory.getLogger(ExecutionRequestHandler.class);
    @Autowired
    private ExecutionConverter executionConverter;
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private ExecutionLogDao executionLogDao;

    public synchronized ExecutionRequestResult handle(ExecutionRequest executionRequest){
        Execution execution = executionDao.findById(executionRequest.getExecutionId());
        ExecutionLog executionLog = new ExecutionLog();
        executionLog.setExecutionId(execution.getId());
        if(!ExecutionStatusType.INPROGRESS.equalsIgnoreCase(execution.getStatus())){
            execution.setStatus(ExecutionStatusType.INPROGRESS);
            executionDao.update(execution);

            executionLog.setLevel(LogLevel.INFO);
            executionLog.setContent("execution has been assigned to "+executionRequest.getExecutor()+" from host: "+executionRequest.getHost());
            executionLogDao.add(executionLog);

            return new ExecutionRequestResult(true, null, executionConverter.convert(execution));
        }else {
            return new ExecutionRequestResult(false, "execution is already in process.", null);
        }
    }
}
