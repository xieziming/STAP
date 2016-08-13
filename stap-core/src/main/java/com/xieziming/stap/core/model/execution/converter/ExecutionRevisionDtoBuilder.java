package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dao.ExecutionContextRevisionDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanRevisionDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionRevisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class ExecutionRevisionDtoBuilder {
    @Autowired
    private ExecutionPlanRevisionDao executionPlanRevisionDao;
    @Autowired
    private ExecutionContextRevisionDao executionContextRevisionDao;

    public ExecutionRevisionDto convert(int executionPlanId) {
        ExecutionRevisionDto executionRevisionDto = new ExecutionRevisionDto();
        executionRevisionDto.setExecutionPlanRevisionList(executionPlanRevisionDao.findAll(executionPlanId));
        executionRevisionDto.setExecutionContextRevisionList(executionContextRevisionDao.findAll(executionPlanId));
        return executionRevisionDto;
    }
}
