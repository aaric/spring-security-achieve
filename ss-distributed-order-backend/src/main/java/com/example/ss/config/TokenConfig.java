package com.example.ss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 令牌配置
 *
 * @author Aaric, created on 2021-08-15T21:52.
 * @version 0.8.0-SNAPSHOT
 */
@Configuration
public class TokenConfig {

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("oauth2.0");
        return jwtAccessTokenConverter;
    }

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter);
    }
}
