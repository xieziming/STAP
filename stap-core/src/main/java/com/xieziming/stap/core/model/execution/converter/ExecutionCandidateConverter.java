package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dao.*;
import com.xieziming.stap.core.model.execution.dto.ExecutionCandidateDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.testcase.converter.TestCaseCandidateConverter;
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
public class ExecutionCandidateConverter {

    @Autowired
    private ExecutionPlanConverter executionPlanConverter;
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private TestCaseCandidateConverter testCaseCandidateConverter;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private ExecutionContextDao executionContextDao;

    public List<ExecutionCandidateDto> convertAll(List<Execution> executionList) {
        List<ExecutionCandidateDto> executionCandidateDtoList = new ArrayList<ExecutionCandidateDto>();
        for (Execution execution : executionList){
            executionCandidateDtoList.add(convert(execution));
        }
        return executionCandidateDtoList;
    }

    public ExecutionCandidateDto convert(Execution execution) {
        ExecutionCandidateDto executionCandidateDto = new ExecutionCandidateDto();
        executionCandidateDto.setId(execution.getId());
        executionCandidateDto.setStartTime(execution.getStartTime());
        executionCandidateDto.setEndTime(execution.getEndTime());
        executionCandidateDto.setStatus(execution.getStatus());
        executionCandidateDto.setResult(execution.getResult());
        executionCandidateDto.setRemark(execution.getRemark());
        executionCandidateDto.setExecutionPlanDto(executionPlanConverter.convert(executionPlanDao.findById(execution.getExecutionPlanId())));
        executionCandidateDto.setTestCaseCandidateDto(testCaseCandidateConverter.convert(testCaseDao.findById(execution.getTestCaseId())));
        executionCandidateDto.setExecutionContext(executionContextDao.findById(execution.getExecutionContextId()));
        return executionCandidateDto;
    }
}
