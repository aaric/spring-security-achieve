package com.example.ss.api.test.impl;

import com.example.ss.api.test.FeatureApi;
import com.example.ss.data.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 测试微服务特性API接口控制器
 *
 * @author Aaric, created on 2021-09-09T15:42.
 * @version 0.12.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/feature")
public class FeatureController implements FeatureApi {

    @Override
    @GetMapping("/normal")
    public ApiData<String> normal() {
        return new ApiData<String>()
                .setData("normal");
    }

    @Override
    @GetMapping("/timeout")
    public ApiData<String> timeout() {
        try {
            // sleep 3s
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ApiData<String>()
                .setData("timeout 3s");
    }
}
