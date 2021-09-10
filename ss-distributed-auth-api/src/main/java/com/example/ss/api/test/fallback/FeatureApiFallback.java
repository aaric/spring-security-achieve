package com.example.ss.api.test.fallback;

import com.example.ss.api.test.feign.FeatureApiFeign;
import com.example.ss.data.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试微服务特性API接口服务降级
 *
 * @author Aaric, created on 2021-09-10T16:25.
 * @version 0.12.0-SNAPSHOT
 */
@Slf4j
@Component
public class FeatureApiFallback implements FeatureApiFeign {

    @Override
    public ApiData<String> normal() {
        log.info("currentThreadName: {}", Thread.currentThread().getName());
        return new ApiData<String>()
                .setData("normal hystrix");
    }

    @Override
    public ApiData<String> timeout() {
        log.info("currentThreadName: {}", Thread.currentThread().getName());
        return new ApiData<String>()
                .setData("timeout hystrix");
    }
}
