package com.example.ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 令牌配置
 *
 * @author Aaric, created on 2021-08-13T23:57.
 * @version 0.7.0-SNAPSHOT
 */
@Configuration
public class TokenConfig {

    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }


}
