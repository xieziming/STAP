package com.xieziming.stap.core.model.execution.dao;

import com.xieziming.stap.core.model.execution.dto.ExecutionDataDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDtoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
@Component
public class ExecutionDataDtoDao {

    @Autowired
    private ExecutionDtoDao executionDtoDao;
    @Autowired
    private TestCaseDtoDao testCaseDtoDao;
    @Autowired
    private TestCaseDao testCaseDao;

    public List<ExecutionDataDto> createDto(List<Execution> executionList) {
        List<ExecutionDataDto> executionDataDtoList = new ArrayList<ExecutionDataDto>();
        for (Execution execution : executionList){
            executionDataDtoList.add(createDto(execution));
        }
        return executionDataDtoList;
    }

    public ExecutionDataDto createDto(Execution execution) {
        ExecutionDataDto executionDataDto = new ExecutionDataDto();
        executionDataDto.setExecutionDto(executionDtoDao.createDto(execution));
        executionDataDto.setTestCaseDto(testCaseDtoDao.createDto(testCaseDao.findById(execution.getTestCaseId())));
        return executionDataDto;
    }
}
