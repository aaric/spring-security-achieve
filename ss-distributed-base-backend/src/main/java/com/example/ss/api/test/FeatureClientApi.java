package com.example.ss.api.test;

import com.example.ss.data.ApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试微服务特性客户端API接口
 *
 * @author Aaric, created on 2021-09-09T16:26.
 * @version 0.12.0-SNAPSHOT
 */
@Api(tags = "测试微服务特性客户端API")
public interface FeatureClientApi {

    @ApiOperation("正常请求客户端")
    ApiData<String> httpNormal();

    @ApiOperation("超时3s客户端")
    ApiData<String> httpTimeout();
}
