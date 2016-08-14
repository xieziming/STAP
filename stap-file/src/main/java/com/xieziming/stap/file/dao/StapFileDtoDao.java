package com.xieziming.stap.file.dao;

import com.xieziming.stap.core.model.file.dto.StapFileDto;
import com.xieziming.stap.core.model.file.pojo.StapFile2;
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
        StapFile2 stapFile2 = stapFileDao.findByPath(path);
        List<StapFileMeta> stapFileMetaList = stapFileMetaDao.findAll(stapFile2.getId());
        return new StapFileDto(stapFile2, stapFileMetaList);
    }

    public void deleteByPath(String path){
        StapFile2 stapFile2 = stapFileDao.findByPath(path);
        stapFileMetaDao.deleteAllByFileId(stapFile2.getId());
        stapFileDao.delete(stapFile2.getId());
    }

    public void put(StapFileDto stapFileDto){
        stapFileDao.put(stapFileDto.getStapFile2());
        stapFileMetaDao.put(stapFileDto.getStapFileMetaList());
    }
}
