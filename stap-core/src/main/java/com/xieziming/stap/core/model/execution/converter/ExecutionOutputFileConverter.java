package com.xieziming.stap.core.model.execution.converter;

import com.xieziming.stap.core.model.execution.dto.ExecutionOutputFileDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionOutputFile;
import com.xieziming.stap.core.model.file.converter.FileConverter;
import com.xieziming.stap.core.model.file.dao.FileDao;
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
    private FileDao fileDao;
    @Autowired
    private FileConverter fileConverter;
    public List<ExecutionOutputFileDto> convertAll(List<ExecutionOutputFile> executionOutputFileList) {
        List<ExecutionOutputFileDto> executionOutputFileDtoList = new ArrayList<ExecutionOutputFileDto>();
        for (ExecutionOutputFile executionOutputFile : executionOutputFileList){
            executionOutputFileDtoList.add(convert(executionOutputFile));
        }
        return executionOutputFileDtoList;
    }

    public ExecutionOutputFileDto convert(ExecutionOutputFile executionOutputFile) {
        ExecutionOutputFileDto executionOutputFileDto = new ExecutionOutputFileDto();
        executionOutputFileDto.setId(executionOutputFile.getId());
        executionOutputFileDto.setType(executionOutputFile.getType());
        executionOutputFileDto.setRemark(executionOutputFile.getRemark());
        executionOutputFileDto.setFileDto(fileConverter.convert(fileDao.findById(executionOutputFile.getFileId())));
        executionOutputFileDto.setLastUpdate(executionOutputFile.getLastUpdate());
        return executionOutputFileDto;
    }
}
