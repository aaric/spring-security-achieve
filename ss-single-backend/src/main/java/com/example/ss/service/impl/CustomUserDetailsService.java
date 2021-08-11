package com.example.ss.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户详情Service接口实现
 *
 * @author Aaric, created on 2021-08-11T15:23.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("origin username: {}", username);
        UserDetails userDetails = User.withUsername("admin")
                .password("admin")
                .authorities("a1")
                .build();
        return userDetails;
    }
}
