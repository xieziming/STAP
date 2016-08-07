package com.xieziming.stap.channel.services;

import com.xieziming.stap.core.model.testcase.builder.TestCaseDtoBuilder;
import com.xieziming.stap.core.model.testcase.builder.TestCaseRevisionDtoBuilder;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import com.xieziming.stap.core.model.testcase.dto.TestCaseDto;
import com.xieziming.stap.core.model.testcase.dto.TestCaseRevisionDto;
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
@RequestMapping("test_case")
public class TestCaseService {
    private static Logger logger = LoggerFactory.getLogger(TestCaseService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private TestCaseDtoBuilder testCaseDtoBuilder;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private TestCaseRevisionDtoBuilder testCaseRevisionDtoBuilder;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<TestCaseDto> getTestCaseList() {
        return null;
    }

    @RequestMapping(value = "{test_case_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public TestCaseDto getTestCase(@PathVariable("test_case_id") int testCaseId) {
        return testCaseDtoBuilder.build(testCaseDao.findById(testCaseId));
    }
    @RequestMapping(value = "{test_case_id}/revision", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public TestCaseRevisionDto getExecutionPlanRevision(@PathVariable("test_case_id") int testCaseId) {
        return testCaseRevisionDtoBuilder.build(testCaseId);
    }

}
