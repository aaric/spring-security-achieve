package com.example.ss.api.test;

import com.example.ss.data.ApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试微服务特性API接口（降级、熔断、限流）
 *
 * @author Aaric, created on 2021-09-09T15:34.
 * @version 0.12.0-SNAPSHOT
 */
@Api(tags = "测试微服务特性API")
public interface FeatureApi {

    @ApiOperation("请求正常")
    @GetMapping("/v1/test/feature/normal")
    ApiData<String> normal();

    @ApiOperation("请求超时3s")
    @GetMapping("/v1/test/feature/normal")
    ApiData<String> timeout();
}
