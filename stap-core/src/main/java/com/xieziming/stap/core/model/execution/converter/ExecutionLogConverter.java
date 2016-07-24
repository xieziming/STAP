package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dto.ExecutionLogDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionLog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionLogConverter {
    public List<ExecutionLogDto> buildAll(List<ExecutionLog> executionLogList) {
        List<ExecutionLogDto> executionLogDtoList = new ArrayList<ExecutionLogDto>();
        for (ExecutionLog executionLog : executionLogList){
            executionLogDtoList.add(build(executionLog));
        }
        return executionLogDtoList;
    }

    public ExecutionLogDto build(ExecutionLog executionLog) {
        ExecutionLogDto executionLogDto = new ExecutionLogDto();
        executionLogDto.setId(executionLog.getId());
        executionLogDto.setLevel(executionLog.getLevel());
        executionLogDto.setContent(executionLog.getContent());
        executionLogDto.setTime(executionLog.getTime());
        return executionLogDto;
    }
}
