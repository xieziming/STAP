package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dao.*;
import com.xieziming.stap.core.model.execution.dto.ExecutionDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.testcase.converter.TestCaseConverter;
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
    private TestCaseConverter testCaseConverter;
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

    public List<ExecutionDto> convertAll(List<Execution> executionList) {
        List<ExecutionDto> executionDtoList = new ArrayList<ExecutionDto>();
        for (Execution execution : executionList){
            executionDtoList.add(convert(execution));
        }
        return executionDtoList;
    }

    public ExecutionDto convert(Execution execution) {
        ExecutionDto executionDto = new ExecutionDto();
        executionDto.setId(execution.getId());
        executionDto.setStartTime(execution.getStartTime());
        executionDto.setEndTime(execution.getEndTime());
        executionDto.setStatus(execution.getStatus());
        executionDto.setResult(execution.getResult());
        executionDto.setRemark(execution.getRemark());
        executionDto.setExecutionPlanDto(executionPlanConverter.convert(executionPlanDao.findById(execution.getExecutionPlanId())));
        executionDto.setTestCaseDto(testCaseConverter.convert(testCaseDao.findById(execution.getTestCaseId())));
        executionDto.setExecutionContext(executionContextDao.findById(execution.getExecutionContextId()));
        executionDto.setExecutionLogDtoList(executionLogConverter.convertAll(executionLogDao.findAllByExecutionId(execution.getId())));
        executionDto.setExecutionOutputFileDtoList(executionOutputFileConverter.convertAll(executionOutputFileDao.findAllByExecution_Id(execution.getId())));
        executionDto.setExecutionOutputTextDtoList(executionOutputTextConverter.convertAll(executionOutputTextDao.findByExecution_Id(execution.getId())));
        executionDto.setExecutionStepDtoList(executionStepConverter.convertAll(executionStepDao.findAllByExecutionId(execution.getId())));
        return executionDto;
    }
}
