package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dao.ExecutionContextDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionBriefDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class ExecutionBriefConverter {
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private ExecutionContextDao executionContextDao;


    public List<ExecutionBriefDto> convertAll(List<Execution> executionList) {
        List<ExecutionBriefDto> executionBriefDtoList = new ArrayList<ExecutionBriefDto>();
        for (Execution execution : executionList){
            executionBriefDtoList.add(convert(execution));
        }
        return executionBriefDtoList;
    }

    public ExecutionBriefDto convert(Execution execution) {
        ExecutionBriefDto executionBriefDto = new ExecutionBriefDto();
        executionBriefDto.setId(execution.getId());
        executionBriefDto.setStartTime(execution.getStartTime());
        executionBriefDto.setEndTime(execution.getEndTime());
        executionBriefDto.setStatus(execution.getStatus());
        executionBriefDto.setResult(execution.getResult());
        executionBriefDto.setRemark(execution.getRemark());
        executionBriefDto.setExecutionPlanId(execution.getExecutionPlanId());
        executionBriefDto.setExecutionPlanName(executionPlanDao.findById(execution.getExecutionPlanId()).getName());
        executionBriefDto.setExecutionContextId(execution.getExecutionContextId());
        executionBriefDto.setExecutionContextName(executionContextDao.findById(execution.getExecutionContextId()).getName());
        executionBriefDto.setTestCaseId(execution.getTestCaseId());
        executionBriefDto.setTestCaseName(testCaseDao.findById(execution.getTestCaseId()).getName());
        return executionBriefDto;
    }
}
