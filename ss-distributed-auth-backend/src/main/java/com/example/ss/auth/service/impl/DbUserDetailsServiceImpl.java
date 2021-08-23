package com.example.ss.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ss.auth.repository.BaseUserRepository;
import com.example.ss.pojo.BaseUser;
import com.example.ss.security.pojo.UserExtend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库用户详情Service接口实现
 *
 * @author Aaric, created on 2021-08-14T07:25.
 * @version 0.7.0-SNAPSHOT
 */
@Slf4j
@Service
public class DbUserDetailsServiceImpl implements UserDetailsService {

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

        if (null != loginUser) {
            List<String> authorityList = baseUserRepository.selectAuthorityList(loginUser.getId());
            log.info("authorityList: {}", authorityList);

            String[] authorities = new String[authorityList.size()];
            authorityList.toArray(authorities);

            UserExtend userExtend = new UserExtend(loginUser.getId(),
                    loginUser.getUsername(),
                    loginUser.getPasswd(),
                    AuthorityUtils.createAuthorityList(authorities));
            return userExtend;
        }
        return null;
    }
}
