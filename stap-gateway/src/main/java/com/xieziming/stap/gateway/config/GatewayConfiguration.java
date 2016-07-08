package com.xieziming.stap.gateway.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.xieziming.stap.gateway.mode.AuthResult;
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

import java.util.concurrent.TimeUnit;

/**
 * Created by Suny on 7/5/16.
 */
@ComponentScan(value = {"com.xieziming.stap.gateway"})
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

    @Bean
    public Cache<String, AuthResult> userCache() {
        Cache<String, AuthResult> userCache = CacheBuilder.newBuilder()
                .maximumSize(10000L)
                .expireAfterAccess(60, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<String, AuthResult>() {
                    public void onRemoval(RemovalNotification<String, AuthResult> notification) {
                        log.info("User " + notification.getKey() + "'s authorization has expired.");
                    }
                })
                .build();
        return userCache;
    }
}
