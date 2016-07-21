package com.xieziming.stap.core.model.execution.builder;

import com.xieziming.stap.core.model.execution.dao.ExecutionLogDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanMetaDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionPlanDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionPlanDtoBuilder {
    @Autowired
    private ExecutionPlanMetaDtoBuilder executionPlanMetaDtoBuilder;
    @Autowired
    private ExecutionPlanMetaDao executionPlanMetaDao;
    @Autowired
    private ExecutionLogDtoBuilder executionLogDtoBuilder;
    @Autowired
    private ExecutionLogDao executionLogDao;

    public List<ExecutionPlanDto> buildAll(List<ExecutionPlan> executionPlanList) {
        List<ExecutionPlanDto> executionPlanDtoList = new ArrayList<ExecutionPlanDto>();
        for (ExecutionPlan executionPlan : executionPlanList){
            executionPlanDtoList.add(build(executionPlan));
        }
        return executionPlanDtoList;
    }

    public ExecutionPlanDto build(ExecutionPlan executionPlan) {
        ExecutionPlanDto executionPlanDto = new ExecutionPlanDto();
        executionPlanDto.setId(executionPlan.getId());
        executionPlanDto.setName(executionPlan.getName());
        executionPlanDto.setDescription(executionPlan.getDescription());
        executionPlanDto.setStatus(executionPlan.getStatus());
        executionPlanDto.setExecutionPlanMetaDtoList(executionPlanMetaDtoBuilder.buildAll(executionPlanMetaDao.findAllByExecutionPlanId(executionPlan.getId())));
        executionPlanDto.setExecutionLogDtoList(executionLogDtoBuilder.buildAll(executionLogDao.findAllByExecutionPlanId(executionPlan.getId())));
        return executionPlanDto;
    }
}
