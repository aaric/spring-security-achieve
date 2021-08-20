package com.example.ss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 配置
 *
 * @author Aaric, created on 2021-08-15T00:20.
 * @version 0.8.0-SNAPSHOT
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs").permitAll()
                // test login token
                .antMatchers("/v1/test/token/login", "/v1/test/token/valid", "/v1/test/token/refresh").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
