package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dao.*;
import com.xieziming.stap.core.model.execution.dto.ExecutionDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.testcase.builder.TestCaseDtoBuilder;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class ExecutionConverter {

    @Autowired
    private ExecutionPlanConverter executionPlanConverter;
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private TestCaseDtoBuilder testCaseDtoBuilder;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private ExecutionContextDao executionContextDao;
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
    private ExecutionStepConverter executionStepConverter;
    @Autowired
    private ExecutionStepDao executionStepDao;

    public List<ExecutionDto> buildAll(List<Execution> executionList) {
        List<ExecutionDto> executionDtoList = new ArrayList<ExecutionDto>();
        for (Execution execution : executionList){
            executionDtoList.add(build(execution));
        }
        return executionDtoList;
    }

    public ExecutionDto build(Execution execution) {
        ExecutionDto executionDto = new ExecutionDto();
        executionDto.setId(execution.getId());
        executionDto.setStartTime(execution.getStartTime());
        executionDto.setEndTime(execution.getEndTime());
        executionDto.setStatus(execution.getStatus());
        executionDto.setResult(execution.getResult());
        executionDto.setRemark(execution.getRemark());
        executionDto.setExecutionPlanDto(executionPlanConverter.convertToDto(executionPlanDao.findById(execution.getExecutionPlanId())));
        executionDto.setTestCaseDto(testCaseDtoBuilder.build(testCaseDao.findById(execution.getTestCaseId())));
        executionDto.setExecutionContext(executionContextDao.findById(execution.getExecutionContextId()));
        executionDto.setExecutionLogDtoList(executionLogConverter.buildAll(executionLogDao.findAllByExecutionId(execution.getId())));
        executionDto.setExecutionOutputFileDtoList(executionOutputFileConverter.buildAll(executionOutputFileDao.findAllByExecution_Id(execution.getId())));
        executionDto.setExecutionOutputTextDtoList(executionOutputTextConverter.buildAll(executionOutputTextDao.findByExecution_Id(execution.getId())));
        executionDto.setExecutionStepDtoList(executionStepConverter.buildAll(executionStepDao.findAllByExecutionId(execution.getId())));
        return executionDto;
    }
}
