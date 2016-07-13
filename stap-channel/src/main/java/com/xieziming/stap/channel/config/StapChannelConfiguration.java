package com.xieziming.stap.channel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Suny on 5/16/16.
 */
@ComponentScan(value = {"com.xieziming.stap"})
@Configuration
@EnableWebMvc

@PropertySources({
        @PropertySource("classpath:stap-channel.properties")
})

public class StapChannelConfiguration extends WebMvcConfigurerAdapter{

    @Value("${stap.file.service.url}")
    private String stapFileServiceUrl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
