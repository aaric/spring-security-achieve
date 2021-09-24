package com.example.ss.config;

import com.example.ss.web.interceptor.FeignTokenRequestInterceptor;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 配置
 *
 * @author Aaric, created on 2021-09-24T09:56.
 * @version 0.13.0-SNAPSHOT
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    FeignTokenRequestInterceptor feignTokenRequestInterceptor() {
        return new FeignTokenRequestInterceptor();
    }
}
