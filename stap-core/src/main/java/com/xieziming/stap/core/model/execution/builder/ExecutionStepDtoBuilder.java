package com.xieziming.stap.core.model.execution.builder;

import com.xieziming.stap.core.model.execution.dao.ExecutionLogDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionOutputFileDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionOutputTextDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionStepDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionStep;
import com.xieziming.stap.core.model.testcase.builder.TestStepDtoBuilder;
import com.xieziming.stap.core.model.testcase.dao.TestStepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionStepDtoBuilder {
    @Autowired
    private ExecutionLogDtoBuilder executionLogDtoBuilder;
    @Autowired
    private ExecutionLogDao executionLogDao;
    @Autowired
    private ExecutionOutputFileDtoBuilder executionOutputFileDtoBuilder;
    @Autowired
    private ExecutionOutputFileDao executionOutputFileDao;
    @Autowired
    private ExecutionOutputTextDtoBuilder executionOutputTextDtoBuilder;
    @Autowired
    private ExecutionOutputTextDao executionOutputTextDao;
    @Autowired
    private TestStepDtoBuilder testStepDtoBuilder;
    @Autowired
    private TestStepDao testStepDao;

    public List<ExecutionStepDto> buildAll(List<ExecutionStep> executionLogList) {
        List<ExecutionStepDto> executionStepDtoList = new ArrayList<ExecutionStepDto>();
        for (ExecutionStep executionStep : executionLogList){
            executionStepDtoList.add(build(executionStep));
        }
        return executionStepDtoList;
    }

    public ExecutionStepDto build(ExecutionStep executionStep) {
        ExecutionStepDto executionStepDto = new ExecutionStepDto();
        executionStepDto.setId(executionStep.getId());
        executionStepDto.setRemark(executionStep.getRemark());
        executionStepDto.setStatus(executionStep.getStatus());
        executionStepDto.setStartTime(executionStep.getStartTime());
        executionStepDto.setEndTime(executionStep.getEndTime());
        executionStepDto.setResult(executionStep.getResult());
        executionStepDto.setExecutionLogDtoList(executionLogDtoBuilder.buildAll(executionLogDao.findAllByExecutionStepId(executionStep.getId())));
        executionStepDto.setExecutionOutputFileDtoList(executionOutputFileDtoBuilder.buildAll(executionOutputFileDao.findAllByExecutionStepId(executionStep.getId())));
        executionStepDto.setExecutionOutputTextDtoList(executionOutputTextDtoBuilder.buildAll(executionOutputTextDao.findByExecutionStepId(executionStep.getId())));
        executionStepDto.setTestStepDto(testStepDtoBuilder.build(testStepDao.findById(executionStep.getTestStepId())));
        return executionStepDto;
    }
}
