package com.example.ss.api.test.impl;

import com.example.ss.api.test.BaseApi;
import com.example.ss.data.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试基础支撑模块API接口控制器
 *
 * @author Aaric, created on 2021-08-13T13:56.
 * @version 0.8.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/order")
public class BaseController implements BaseApi {

    @Value("${base.data-id}")
    private String dataId;

    @Override
    @GetMapping("/test/getDataId")
    @PreAuthorize("hasAuthority('a3')")
    public ApiData<String> getDataId() {
        return new ApiData<String>()
                .setData("dataId: " + dataId);
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        }
        return null;
    }

    @Override
    @GetMapping("/r1")
    @PreAuthorize("hasAuthority('a1')")
    public ApiData<String> r1() {
        return new ApiData<String>()
                .setData(getUsername() + " visit r1");
    }

    @Override
    @GetMapping("/r2")
    @PreAuthorize("hasAuthority('a2')")
    public ApiData<String> r2() {
        return new ApiData<String>()
                .setData(getUsername() + " visit r2");
    }
}
