package com.xieziming.stap.gateway.config;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Suny on 7/5/16.
 */
@ComponentScan(value = {"com.xieziming.stap.core", "com.xieziming.stap.gateway"})
@Configuration
@EnableWebMvc
public class GatewayConfiguration {
    private static final Logger log = LoggerFactory.getLogger(GatewayConfiguration.class);
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(){
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        pcm.setDefaultMaxPerRoute(50);
        pcm.setMaxTotal(50);
        return pcm;
    }


}
