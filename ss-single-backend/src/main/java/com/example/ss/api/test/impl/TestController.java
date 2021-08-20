package com.example.ss.api.test.impl;

import com.example.ss.api.test.TestApi;
import com.example.ss.data.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试模块API接口控制器
 *
 * @author Aaric, created on 2021-08-10T13:56.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@RestController
public class TestController implements TestApi {

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        }
        return null;
    }

    @Override
    @GetMapping("/ok")
    public ApiData<String> ok() {
        return new ApiData<String>()
                .setData(getUsername() + " login success");
    }

    @Override
    @GetMapping("/r/r1")
    @PreAuthorize("hasAuthority('a1')")
    public ApiData<String> r1() {
        return new ApiData<String>()
                .setData(getUsername() + " visit r1");
    }

    @Override
    @GetMapping("/r/r2")
    @PreAuthorize("hasAuthority('a2')")
    public ApiData<String> r2() {
        return new ApiData<String>()
                .setData(getUsername() + " visit r2");
    }
}
