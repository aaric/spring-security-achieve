package com.example.ss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 配置
 *
 * @author Aaric, created on 2021-08-10T14:59.
 * @version 0.1.0-SNAPSHOT
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("admin").password("admin").authorities("a1").and()
//                .withUser("test").password("test").authorities("a2");
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/test/test/r1").hasAnyAuthority("a1")
                .antMatchers("/api/v1/test/test/r2").hasAnyAuthority("a2")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successForwardUrl("/api/v1/test/test/ok")
                .and()
                .csrf().disable();
    }
}
