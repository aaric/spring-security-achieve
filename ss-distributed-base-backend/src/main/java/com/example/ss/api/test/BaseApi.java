package com.example.ss.api.test;

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
    String getDataId();

    @ApiOperation("R1")
    String r1();

    @ApiOperation("R2")
    String r2();
}
