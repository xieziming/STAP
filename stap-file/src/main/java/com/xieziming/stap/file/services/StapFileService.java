package com.xieziming.stap.file.services;

import com.xieziming.stap.core.exceptions.StapException;
import com.xieziming.stap.core.model.file.dto.StapFileDto;
import com.xieziming.stap.core.model.file.pojo.StapFile2;
import com.xieziming.stap.file.dao.StapFileDtoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Suny on 5/16/16.
 */
@Controller
public class StapFileService {
    private static Logger logger = LoggerFactory.getLogger(StapFileService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private StapFileDtoDao stapFileDtoDao;

    @RequestMapping(value = "file/{path}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public StapFileDto getFile(@PathVariable("path") String path) {
        return stapFileDtoDao.findByPath(path);
    }

    @RequestMapping(value = "file", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseStatus(HttpStatus.OK)
    public void pushFile(@RequestBody final StapFileDto stapFileDto) {
        stapFileDtoDao.put(stapFileDto);
    }

    @RequestMapping(value = "file/{path}/content", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE+UTF8)
    @ResponseBody
    public byte[] getFileContent(@PathVariable("path") String path) {
        StapFile2 stapFile2 = stapFileDtoDao.findByPath(path).getStapFile2();
        return stapFile2.getContent();
    }

    @RequestMapping(value = "file/{path}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseStatus(HttpStatus.OK)
    public void deleteFile(@PathVariable("path") String path) {
        stapFileDtoDao.deleteByPath(path);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StapException> handleAllException(Exception exception) {
        logger.error("Stap File Service Exception", exception);
        return new ResponseEntity<StapException>(new StapException(exception),HttpStatus.BAD_REQUEST);
    }
}

