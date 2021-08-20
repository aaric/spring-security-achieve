package com.example.ss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 令牌配置
 *
 * @author Aaric, created on 2021-08-15T21:52.
 * @version 0.8.0-SNAPSHOT
 */
@Configuration
public class TokenConfig {

    /*@Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }*/

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("oauth2:");
        return redisTokenStore;
    }
}
