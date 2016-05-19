package com.xieziming.stap.execution.config;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Suny on 5/16/16.
 */
@ComponentScan(value = {"com.xieziming.stap.execution.services"})
@Configuration
@EnableWebMvc
/*
@PropertySources({
        @PropertySource("classpath:properties/stap-file-db.properties")
})
*/
public class ConextConfig extends WebMvcConfigurerAdapter{
    /*
    @Value("${db.host}")
    private String dbHost;

    @Value("${db.port}")
    private int dbPort;

    @Value("${db.name}")
    private String dbName;
    */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
