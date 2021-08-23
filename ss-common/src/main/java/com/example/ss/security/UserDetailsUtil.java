package com.example.ss.security;

import com.example.ss.security.pojo.UserExtend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Spring Security UserDetails工具类
 *
 * @author Aaric, created on 2021-08-20T14:32.
 * @version 0.8.0-SNAPSHOT
 */
@Slf4j
public final class UserDetailsUtil {

    /**
     * 获取用户扩展信息
     *
     * @return
     */
    private static UserExtend getUserExtend() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null != principal && principal instanceof UserExtend) {
            return (UserExtend) principal;
        }
        return null;
    }

    /**
     * 获取用户ID（当前登录）
     *
     * @return
     */
    public static Long getUserId() {
        UserExtend userExtend = getUserExtend();
        if (null != userExtend) {
            log.debug("userExtend: {}", userExtend);
            return userExtend.getId();
        }
        return null;
    }

    /**
     * 获取用户名（当前登录）
     *
     * @return
     */
    public static String getUsername() {
        UserExtend userExtend = getUserExtend();
        if (null != userExtend) {
            log.debug("userExtend: {}", userExtend);
            return userExtend.getUsername();
        }
        return null;
    }
}
