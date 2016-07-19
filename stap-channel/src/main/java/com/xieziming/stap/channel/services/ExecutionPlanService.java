package com.xieziming.stap.channel.services;

import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionDtoDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlan;
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
    private ExecutionDtoDao executionDtoDao;
    @Autowired
    private ExecutionDao executionDao;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionPlan> getAllExecutionPlans() {
        return executionPlanDao.findAll();
    }

    @RequestMapping(value = "{execution_plan_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionPlan getExecutionPlan(@PathVariable("execution_plan_id") int executionPlanId) {
        return executionPlanDao.findById(executionPlanId);
    }

    @RequestMapping(value = "{execution_plan_id}/executions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionDto> getExecutions(@PathVariable("execution_plan_id") int executionPlanId) {
        List<Execution> executionList = executionDao.findAllByExecutionPlanId(executionPlanId);
        return executionDtoDao.createDto(executionList);
    }


}
