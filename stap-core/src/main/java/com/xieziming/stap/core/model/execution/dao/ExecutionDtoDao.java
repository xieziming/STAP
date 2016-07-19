package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.dto.ExecutionDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.execution.pojo.ExecutionContext;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlan;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class ExecutionDtoDao {

    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private ExecutionContextDao executionContextDao;
    @Autowired
    private ExecutionPlanDao executionPlanDao;

    public List<ExecutionDto> createDto(List<Execution> executionList) {
        List<ExecutionDto> executionDtoList = new ArrayList<ExecutionDto>();
        for (Execution execution : executionList){
            executionDtoList.add(createDto(execution));
        }
        return executionDtoList;
    }

    public ExecutionDto createDto(Execution execution) {
        TestCase testCase = testCaseDao.findById(execution.getTestCaseId());
        ExecutionPlan executionPlan = executionPlanDao.findById(execution.getExecutionPlanId());
        ExecutionContext executionContext = executionContextDao.findById(execution.getExecutionContextId());
        ExecutionDto executionDto = new ExecutionDto();
        executionDto.setId(execution.getId());
        executionDto.setExecutionPlanId(execution.getExecutionPlanId());
        executionDto.setExecutionPlanName(executionPlan.getName());
        executionDto.setTestCaseId(execution.getTestCaseId());
        executionDto.setTestCaseName(testCase.getName());
        executionDto.setExecutionContextId(execution.getExecutionContextId());
        executionDto.setExecutionContextName(executionContext.getName());
        executionDto.setStartTime(execution.getStartTime());
        executionDto.setEndTime(execution.getEndTime());
        executionDto.setStatus(execution.getStatus());
        executionDto.setResult(execution.getResult());
        executionDto.setRemark(execution.getRemark());
        return executionDto;
    }
}
