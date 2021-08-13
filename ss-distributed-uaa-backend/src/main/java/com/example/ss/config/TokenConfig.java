package com.example.ss.config;

import org.springframework.context.annotation.Configuration;

/**
 * 令牌配置
 *
 * @author Aaric, created on 2021-08-13T23:57.
 * @version 0.7.0-SNAPSHOT
 */
@Configuration
public class TokenConfig {

//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//
//    @Bean
//    TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }
//
//    @Bean
//    AuthorizationServerTokenServices tokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setClientDetailsService(clientDetailsService);
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setTokenStore(tokenStore);
//        tokenServices.setAccessTokenValiditySeconds(24 * 60 * 60);
//        tokenServices.setRefreshTokenValiditySeconds(3 * 24 * 60 * 60);
//        return tokenServices;
//    }
}
