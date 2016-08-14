package com.xieziming.stap.core.model.file.converter;

import com.xieziming.stap.core.model.file.dao.FileDao;
import com.xieziming.stap.core.model.file.dao.FileMetaDao;
import com.xieziming.stap.core.model.file.dto.FileDto;
import com.xieziming.stap.core.model.file.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/14/16.
 */
@Component
public class FileConverter {
    @Autowired
    private FileDao fileDao;
    @Autowired
    private FileMetaDao fileMetaDao;
    @Autowired
    private FileMetaConverter fileMetaConverter;

    public List<FileDto> convertAll(List<File> fileList){
        List<FileDto> fileDtoList = new ArrayList<FileDto>();
        for (File file : fileList){
            fileDtoList.add(convert(file));
        }
        return fileDtoList;
    }

    public FileDto convert(File file){
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getId());
        fileDto.setName(file.getName());
        fileDto.setUrl(file.getUrl());
        fileDto.setFileMetaDtoList(fileMetaConverter.convertAll(fileMetaDao.findAllByFileId(file.getId())));
        return fileDto;
    }
}
