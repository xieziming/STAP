package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dto.ExecutionOutputFileDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionOutputFile;
import com.xieziming.stap.core.model.file.dao.FileReferenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 7/21/16.
 */
@Component
public class ExecutionOutputFileConverter {
    @Autowired
    private FileReferenceDao fileReferenceDao;
    public List<ExecutionOutputFileDto> buildAll(List<ExecutionOutputFile> executionOutputFileList) {
        List<ExecutionOutputFileDto> executionOutputFileDtoList = new ArrayList<ExecutionOutputFileDto>();
        for (ExecutionOutputFile executionOutputFile : executionOutputFileList){
            executionOutputFileDtoList.add(build(executionOutputFile));
        }
        return executionOutputFileDtoList;
    }

    public ExecutionOutputFileDto build(ExecutionOutputFile executionOutputFile) {
        ExecutionOutputFileDto executionOutputFileDto = new ExecutionOutputFileDto();
        executionOutputFileDto.setId(executionOutputFile.getId());
        executionOutputFileDto.setType(executionOutputFile.getType());
        executionOutputFileDto.setRemark(executionOutputFile.getRemark());
        executionOutputFileDto.setFileReference(fileReferenceDao.findById(executionOutputFile.getFileId()));
        executionOutputFileDto.setLastUpdate(executionOutputFile.getLastUpdate());
        return executionOutputFileDto;
    }
}
