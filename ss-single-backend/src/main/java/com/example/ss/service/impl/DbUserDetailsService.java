package com.example.ss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ss.pojo.BaseUser;
import com.example.ss.test.repository.BaseUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库用户详情Service接口实现
 *
 * @author Aaric, created on 2021-08-11T15:23.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@Service
public class DbUserDetailsService implements UserDetailsService {

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("origin username: {}", username);

        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper<BaseUser>();
        queryWrapper.lambda()
                .eq(BaseUser::getUsername, username)
                .eq(BaseUser::getIsDeleted, 0);
        BaseUser loginUser = baseUserRepository.selectOne(queryWrapper);
        log.info("loginUser: {}", loginUser);

        UserDetails userDetails = null;
        if (null != loginUser) {
            List<String> authorityList = baseUserRepository.selectAuthorityList(loginUser.getId());
            log.info("authorityList: {}", authorityList);

            String[] authorities = new String[authorityList.size()];
            authorityList.toArray(authorities);
            userDetails = User.withUsername(loginUser.getUsername())
                    .password(loginUser.getPasswd())
                    .authorities(authorities)
                    .build();
        }
        return userDetails;
    }
}
