package com.example.ss.api.test.impl;

import com.example.ss.api.test.FeatureClientApi;
import com.example.ss.api.test.feign.FeatureApiFeign;
import com.example.ss.data.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试微服务特性客户端API接口控制器
 *
 * @author Aaric, created on 2021-09-09T16:29.
 * @version 0.12.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/featureClient")
public class FeatureClientController implements FeatureClientApi {

    @Autowired
    private FeatureApiFeign featureApiFeign;

    @Override
    @GetMapping("/httpNormal")
    public ApiData<String> httpNormal() {
        return featureApiFeign.normal();
    }

    @Override
    @GetMapping("/httpTimeout")
    //@HystrixCommand(fallbackMethod = "httpTimeoutFallback")
    public ApiData<String> httpTimeout() {
        return featureApiFeign.timeout();
    }

    private ApiData<String> httpTimeoutFallback() {
        return new ApiData<String>()
                .setData("timeout hystrix");
    }
}
