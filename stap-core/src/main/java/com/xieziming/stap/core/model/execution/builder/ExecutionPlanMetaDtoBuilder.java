package com.xieziming.stap.core.model.execution.builder;

import com.xieziming.stap.core.model.execution.dto.ExecutionPlanMetaDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlanMeta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionPlanMetaDtoBuilder {
    public List<ExecutionPlanMetaDto> buildAll(List<ExecutionPlanMeta> executionPlanMetaList) {
        List<ExecutionPlanMetaDto> executionPlanMetaDtoList = new ArrayList<ExecutionPlanMetaDto>();
        for (ExecutionPlanMeta executionPlanMeta : executionPlanMetaList){
            executionPlanMetaDtoList.add(build(executionPlanMeta));
        }
        return executionPlanMetaDtoList;
    }

    public ExecutionPlanMetaDto build(ExecutionPlanMeta executionPlanMeta) {
        ExecutionPlanMetaDto executionPlanMetaDto = new ExecutionPlanMetaDto();
        executionPlanMetaDto.setId(executionPlanMeta.getId());
        executionPlanMetaDto.setMetaKey(executionPlanMeta.getMetaKey());
        executionPlanMetaDto.setMetaValue(executionPlanMeta.getMetaValue());
        executionPlanMetaDto.setLastUpdate(executionPlanMeta.getLastUpdate());
        return executionPlanMetaDto;
    }
}
