package com.xieziming.stap.channel.services;

import com.xieziming.stap.core.execution.ExecutionController;
import com.xieziming.stap.core.execution.ExecutionRequest;
import com.xieziming.stap.core.execution.ExecutionResponse;
import com.xieziming.stap.core.model.comment.converter.CommentConverter;
import com.xieziming.stap.core.model.comment.dao.CommentDao;
import com.xieziming.stap.core.model.comment.dto.CommentDto;
import com.xieziming.stap.core.model.execution.converter.ExecutionBriefConverter;
import com.xieziming.stap.core.model.execution.converter.ExecutionConverter;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionBriefDto;
import com.xieziming.stap.core.model.execution.dto.ExecutionDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("execution")
public class ExecutionService {
    private static Logger logger = LoggerFactory.getLogger(ExecutionService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionConverter executionConverter;
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private ExecutionController executionController;
    @Autowired
    private ExecutionBriefConverter executionBriefConverter;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionDto> getExecutions() {
        List<Execution> executionList = executionDao.findAll();
        return executionConverter.convertAll(executionList);
    }

    @RequestMapping(value = "{execution_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionDto getExecution(@PathVariable("execution_id") int executionId) {
        Execution execution = executionDao.findById(executionId);
        return executionConverter.convert(execution);
    }

    @RequestMapping(value = "{execution_id}/brief", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionBriefDto getExecutionBrief(@PathVariable("execution_id") int executionId) {
        Execution execution = executionDao.findById(executionId);
        return executionBriefConverter.convert(execution);
    }

    @RequestMapping(value = "{execution_id}/request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionResponse requestExecution(@PathVariable("execution_id") int executionId) {
        return executionController.request(new ExecutionRequest(executionId, "suny", "124"));
    }

    @RequestMapping(value = "{execution_id}/comment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<CommentDto> getComments(@PathVariable("execution_id") int executionId) {
        return commentConverter.convertAll(commentDao.findAllByExecutionId(executionId));
    }
}
