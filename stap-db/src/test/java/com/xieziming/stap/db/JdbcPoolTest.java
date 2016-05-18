package com.xieziming.stap.db;

import org.junit.Test;

import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */

public class JdbcPoolTest {
    @Test
    public void testPool() {
        List<String> testCaseNames = StapDbUtil.getJdbcTemplate().queryForList("select Name from test_case", String.class);
        for(String testCaseName : testCaseNames) {
            System.out.println(testCaseName);
        }
    }
}