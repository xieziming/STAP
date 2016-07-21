package com.xieziming.stap.core.execution;

import com.xieziming.stap.core.constants.ExecutionStatusType;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.builder.ExecutionDtoBuilder;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionRequestHandler {
    private static Logger log = LoggerFactory.getLogger(ExecutionRequestHandler.class);
    @Autowired
    ExecutionDtoBuilder executionDtoBuilder;
    @Autowired
    ExecutionDao executionDao;

    public synchronized ExecutionResponse handle(ExecutionRequest executionRequest){
        Execution execution = executionDao.findById(executionRequest.getExecutionId());
        if(ExecutionStatusType.INPROGRESS != execution.getStatus()){
            log.info("execution {} has assigned to ", executionRequest.getExecutionId(), executionRequest.getRequester()+"("+executionRequest.getFromIp()+")");
            execution.setStatus(ExecutionStatusType.INPROGRESS);
            executionDao.update(execution);
            return new ExecutionResponse("yes", executionDtoBuilder.build(execution));
        }else {
            log.info("{} 's request to run execution {} has been rejected!", executionRequest.getRequester()+"("+executionRequest.getFromIp()+")", executionRequest.getExecutionId());
            return new ExecutionResponse("not", null);
        }
    }
}
