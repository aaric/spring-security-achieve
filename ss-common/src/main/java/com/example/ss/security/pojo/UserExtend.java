package com.example.ss.security.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Spring Security User 扩展类
 *
 * @author Aaric, created on 2021-08-23T09:16.
 * @version 0.11.0-SNAPSHOT
 */
public class UserExtend extends User {

    /**
     * 用户ID
     */
    private Long id;

    public UserExtend(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserExtend(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
