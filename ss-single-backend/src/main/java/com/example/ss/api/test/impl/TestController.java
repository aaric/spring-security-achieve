package com.example.ss.api.test.impl;

import com.example.ss.api.test.TestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试模块API接口控制器
 *
 * @author Aaric, created on 2021-08-10T13:56.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test/test")
public class TestController implements TestApi {

    @Override
    @GetMapping("/ok")
    public String ok() {
        return "r1";
    }

    @Override
    @GetMapping("/r1")
    public String r1() {
        return "r1";
    }

    @Override
    @GetMapping("/r2")
    public String r2() {
        return "r2";
    }
}
