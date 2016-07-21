package com.xieziming.stap.channel.services;

import com.xieziming.stap.core.model.execution.builder.ExecutionDtoBuilder;
import com.xieziming.stap.core.model.execution.builder.ExecutionPlanDtoBuilder;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionDto;
import com.xieziming.stap.core.model.execution.dto.ExecutionPlanDto;
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
@RequestMapping("execution_plan")
public class ExecutionPlanService {
    private static Logger logger = LoggerFactory.getLogger(ExecutionPlanService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private ExecutionPlanDtoBuilder executionPlanDtoBuilder;
    @Autowired
    private ExecutionDtoBuilder executionDtoBuilder;
    @Autowired
    private ExecutionDao executionDao;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionPlanDto> getAllExecutionPlans() {
        return executionPlanDtoBuilder.buildAll(executionPlanDao.findAll());
    }

    @RequestMapping(value = "{execution_plan_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionPlanDto getExecutionPlan(@PathVariable("execution_plan_id") int executionPlanId) {
        return executionPlanDtoBuilder.build(executionPlanDao.findById(executionPlanId));
    }

    @RequestMapping(value = "{execution_plan_id}/execution_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionDto> getExecutions(@PathVariable("execution_plan_id") int executionPlanId) {
        return executionDtoBuilder.buildAll(executionDao.findAllByExecutionPlanId(executionPlanId));
    }
}
