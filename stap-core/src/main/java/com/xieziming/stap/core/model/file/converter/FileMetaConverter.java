package com.xieziming.stap.core.model.file.converter;

import com.xieziming.stap.core.model.file.dto.FileMetaDto;
import com.xieziming.stap.core.model.file.pojo.FileMeta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/14/16.
 */
@Component
public class FileMetaConverter {

    public List<FileMetaDto> convertAll(List<FileMeta> fileMetaList){
        List<FileMetaDto> fileMetaDtoList = new ArrayList<FileMetaDto>();
        for (FileMeta fileMeta : fileMetaList){
            fileMetaDtoList.add(convert(fileMeta));
        }
        return fileMetaDtoList;
    }

    public FileMetaDto convert(FileMeta fileMeta){
        FileMetaDto fileMetaDto = new FileMetaDto();
        fileMetaDto.setId(fileMeta.getId());
        fileMetaDto.setMetaKey(fileMeta.getMetaKey());
        fileMetaDto.setMetaValue(fileMeta.getMetaValue());
        fileMetaDto.setLastUpdate(fileMeta.getLastUpdate());
        return fileMetaDto;
    }
}
