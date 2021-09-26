package com.example.ss.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * Spring Security 配置
 *
 * @author Aaric, created on 2021-08-10T14:59.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("admin").authorities("a1").and()
                .withUser("test").password("test").authorities("a2");

    }*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                /*.antMatchers("/r/r1").hasAnyAuthority("a1")
                .antMatchers("/r/r2").hasAnyAuthority("a2")
                .anyRequest().permitAll()*/
                // Knife4j Swagger 允许访问资源
                .antMatchers("/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs").permitAll()
                .antMatchers("/logstash").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // SpringMVC不支持POST请求直接返回页面，successForwardUrl会报“405 Method Not Allowed”
                .defaultSuccessUrl("/ok", true)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    log.error("access denied", accessDeniedException);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                })
                .and()
                .csrf().disable();
    }
}
