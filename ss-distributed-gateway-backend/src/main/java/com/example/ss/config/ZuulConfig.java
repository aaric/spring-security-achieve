package com.example.ss.config;

import com.example.ss.zuul.filter.Oauth2TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Zuul 配置
 *
 * @author Aaric, created on 2021-08-20T15:26.
 * @version 0.9.0-SNAPSHOT
 */
@Configuration
public class ZuulConfig {

    @Bean
    Oauth2TokenFilter oauth2TokenFilter() {
        return new Oauth2TokenFilter();
    }
}
