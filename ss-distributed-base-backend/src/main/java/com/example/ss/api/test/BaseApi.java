package com.example.ss.api.test;

import com.example.ss.data.ApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试基础支撑模块API接口
 *
 * @author Aaric, created on 2021-08-13T13:55.
 * @version 0.8.0-SNAPSHOT
 */
@Api(tags = "测试基础支撑模块API")
public interface BaseApi {

    @ApiOperation("获取DataId")
    ApiData<String> getDataId();

    @ApiOperation("R1")
    ApiData<String> r1();

    @ApiOperation("R2")
    ApiData<String> r2();

    @ApiOperation("R3")
    ApiData<String> r3();
}
