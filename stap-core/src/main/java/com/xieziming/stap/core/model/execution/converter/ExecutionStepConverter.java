package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dao.ExecutionLogDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionOutputFileDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionOutputTextDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionStepDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionStep;
import com.xieziming.stap.core.model.testcase.converter.TestStepConverter;
import com.xieziming.stap.core.model.testcase.dao.TestStepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionStepConverter {
    @Autowired
    private ExecutionLogConverter executionLogConverter;
    @Autowired
    private ExecutionLogDao executionLogDao;
    @Autowired
    private ExecutionOutputFileConverter executionOutputFileConverter;
    @Autowired
    private ExecutionOutputFileDao executionOutputFileDao;
    @Autowired
    private ExecutionOutputTextConverter executionOutputTextConverter;
    @Autowired
    private ExecutionOutputTextDao executionOutputTextDao;
    @Autowired
    private TestStepConverter testStepConverter;
    @Autowired
    private TestStepDao testStepDao;

    public List<ExecutionStepDto> convertAll(List<ExecutionStep> executionLogList) {
        List<ExecutionStepDto> executionStepDtoList = new ArrayList<ExecutionStepDto>();
        for (ExecutionStep executionStep : executionLogList){
            executionStepDtoList.add(convert(executionStep));
        }
        return executionStepDtoList;
    }

    public ExecutionStepDto convert(ExecutionStep executionStep) {
        ExecutionStepDto executionStepDto = new ExecutionStepDto();
        executionStepDto.setId(executionStep.getId());
        executionStepDto.setRemark(executionStep.getRemark());
        executionStepDto.setStatus(executionStep.getStatus());
        executionStepDto.setStartTime(executionStep.getStartTime());
        executionStepDto.setEndTime(executionStep.getEndTime());
        executionStepDto.setResult(executionStep.getResult());
        executionStepDto.setExecutionLogDtoList(executionLogConverter.convertAll(executionLogDao.findAllByExecutionStepId(executionStep.getId())));
        executionStepDto.setExecutionOutputFileDtoList(executionOutputFileConverter.convertAll(executionOutputFileDao.findAllByExecutionStepId(executionStep.getId())));
        executionStepDto.setExecutionOutputTextDtoList(executionOutputTextConverter.convertAll(executionOutputTextDao.findByExecutionStepId(executionStep.getId())));
        executionStepDto.setTestStepDto(testStepConverter.convert(testStepDao.findById(executionStep.getTestStepId())));
        return executionStepDto;
    }
}
