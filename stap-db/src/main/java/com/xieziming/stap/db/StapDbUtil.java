package com.xieziming.stap.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Suny on 5/10/16.
 */
public class StapDbUtil {
    private static JdbcTemplate jdbcTemplate;
    public static JdbcTemplate getJdbcTemplate(){
        if(jdbcTemplate == null){
            synchronized (StapDbUtil.class){
                if(jdbcTemplate == null){
                    ApplicationContext context = new ClassPathXmlApplicationContext("stap-db-context.xml");
                    jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
                }
            }
        }
        return jdbcTemplate;
    }

}
