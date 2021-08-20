package com.example.ss.api.test;

import com.example.ss.data.ApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试授权认证模块API接口
 *
 * @author Aaric, created on 2021-08-13T13:55.
 * @version 0.8.0-SNAPSHOT
 */
@Api(tags = "测试授权认证模块API")
public interface AuthApi {

    @ApiOperation("获取DataId")
    ApiData<String> getDataId();
}
