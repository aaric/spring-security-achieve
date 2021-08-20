package com.example.ss.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详情工具类
 *
 * @author Aaric, created on 2021-08-20T14:32.
 * @version 0.8.0-SNAPSHOT
 */
public final class UserDetailsUtil {

    /**
     * 获取用户名（当前登录）
     *
     * @return
     */
    public static String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        }
        return null;
    }
}
