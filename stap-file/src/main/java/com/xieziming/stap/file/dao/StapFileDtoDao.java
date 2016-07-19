package com.xieziming.stap.file.dao;

import com.xieziming.stap.core.model.file.dto.StapFileDto;
import com.xieziming.stap.core.model.file.pojo.StapFile;
import com.xieziming.stap.core.model.file.pojo.StapFileMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suny on 7/19/16.
 */
@Component
public class StapFileDtoDao {
    @Autowired
    private StapFileMetaDao stapFileMetaDao;
    @Autowired
    private StapFileDao stapFileDao;

    public StapFileDto findByPath(String path){
        StapFile stapFile = stapFileDao.findByPath(path);
        List<StapFileMeta> stapFileMetaList = stapFileMetaDao.findAll(stapFile.getId());
        return new StapFileDto(stapFile, stapFileMetaList);
    }

    public void deleteByPath(String path){
        StapFile stapFile = stapFileDao.findByPath(path);
        stapFileMetaDao.deleteAllByFileId(stapFile.getId());
        stapFileDao.delete(stapFile.getId());
    }

    public void put(StapFileDto stapFileDto){
        stapFileDao.put(stapFileDto.getStapFile());
        stapFileMetaDao.put(stapFileDto.getStapFileMetaList());
    }
}
