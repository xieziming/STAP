package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dto.ExecutionOutputTextDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionOutputText;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionOutputTextConverter {
    public List<ExecutionOutputTextDto> buildAll(List<ExecutionOutputText> executionOutputTextList) {
        List<ExecutionOutputTextDto> executionOutputTextDtoList = new ArrayList<ExecutionOutputTextDto>();
        for (ExecutionOutputText executionOutputText : executionOutputTextList){
            executionOutputTextDtoList.add(build(executionOutputText));
        }
        return executionOutputTextDtoList;
    }

    public ExecutionOutputTextDto build(ExecutionOutputText executionOutputText) {
        ExecutionOutputTextDto executionOutputTextDto = new ExecutionOutputTextDto();
        executionOutputTextDto.setId(executionOutputText.getId());
        executionOutputTextDto.setType(executionOutputText.getType());
        executionOutputTextDto.setRemark(executionOutputText.getRemark());
        executionOutputTextDto.setField(executionOutputText.getField());
        executionOutputTextDto.setValue(executionOutputText.getValue());
        executionOutputTextDto.setLastUpdate(executionOutputText.getLastUpdate());
        return executionOutputTextDto;
    }
}
