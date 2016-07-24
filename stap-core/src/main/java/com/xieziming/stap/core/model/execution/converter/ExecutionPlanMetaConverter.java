package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dao.ExecutionPlanMetaDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionPlanMetaDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlanMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionPlanMetaConverter {
    @Autowired
    private ExecutionPlanMetaDao executionPlanMetaDao;

    public List<ExecutionPlanMetaDto> convertAllToDto(List<ExecutionPlanMeta> executionPlanMetaList) {
        List<ExecutionPlanMetaDto> executionPlanMetaDtoList = new ArrayList<ExecutionPlanMetaDto>();
        for (ExecutionPlanMeta executionPlanMeta : executionPlanMetaList){
            executionPlanMetaDtoList.add(convertToDto(executionPlanMeta));
        }
        return executionPlanMetaDtoList;
    }

    public ExecutionPlanMetaDto convertToDto(ExecutionPlanMeta executionPlanMeta) {
        ExecutionPlanMetaDto executionPlanMetaDto = new ExecutionPlanMetaDto();
        executionPlanMetaDto.setId(executionPlanMeta.getId());
        executionPlanMetaDto.setMetaType(executionPlanMeta.getMetaType());
        executionPlanMetaDto.setMetaKey(executionPlanMeta.getMetaKey());
        executionPlanMetaDto.setMetaValue(executionPlanMeta.getMetaValue());
        executionPlanMetaDto.setLastUpdate(executionPlanMeta.getLastUpdate());
        return executionPlanMetaDto;
    }

    public ExecutionPlanMeta convertToPojo(ExecutionPlanMetaDto executionPlanMetaDto) {
        return executionPlanMetaDao.findById(executionPlanMetaDto.getId());
    }
}
