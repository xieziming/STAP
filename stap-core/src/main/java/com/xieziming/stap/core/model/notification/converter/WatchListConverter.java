package com.xieziming.stap.core.model.notification.converter;

import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanDao;
import com.xieziming.stap.core.model.notification.dto.WatchListDto;
import com.xieziming.stap.core.model.notification.pojo.WatchList;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/13/16.
 */
@Component
public class WatchListConverter {
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private ExecutionDao executionDao;

    public List<WatchListDto> convertAll(List<WatchList> watchLists){
        List<WatchListDto> watchListDtoList = new ArrayList<WatchListDto>();
        for (WatchList watchList : watchLists){
            watchListDtoList.add(convert(watchList));
        }
        return watchListDtoList;
    }

    public WatchListDto convert(WatchList watchList){
        WatchListDto watchListDto = new WatchListDto();
        watchListDto.setId(watchList.getId());

        int testCsaeId = watchList.getTestCaseId();
        if(testCsaeId > 0) {
            watchListDto.setSource("testCase");
            watchListDto.setSourceId(testCsaeId);
            watchListDto.setSourceName(testCaseDao.findById(testCsaeId).getName());
        }

        int executionPlanId = watchList.getExecutionPlanId();
        if(executionPlanId > 0){
            watchListDto.setSource("executionPlan");
            watchListDto.setSourceId(executionPlanId);
            watchListDto.setSourceName(executionPlanDao.findById(executionPlanId).getName());
        }

        int executionId = watchList.getExecutionId();
        if(executionId > 0) {
            watchListDto.setSource("execution");
            watchListDto.setSourceId(executionId);
            watchListDto.setSourceName(testCaseDao.findById(executionDao.findById(executionId).getTestCaseId()).getName());
        }

        watchListDto.setUserId(watchList.getUserId());
        return watchListDto;
    }
}
