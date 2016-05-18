package com.xieziming.stap.dao.execution;

import com.xieziming.stap.dao.BaseDao;

import java.util.List;

/**
 * Created by Suny on 5/9/16.
 */
public interface ExecutionDao<Execution> extends BaseDao<Execution> {
    /**
     * Generic list all by plan id
     * @param planId
     * @return
     */
    public List<Execution> findByPlanId(int planId);

}
