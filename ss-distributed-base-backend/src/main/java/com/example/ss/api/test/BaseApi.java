package com.example.ss.api.test;

import com.example.ss.data.ApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

    @ApiOperation("更新当前Authorities")
    ApiData<String> updateGrantedAuthorities(@ApiParam("新权限列表") String[] newAuthorities);

    @ApiOperation("R1")
    ApiData<String> r1();

    @ApiOperation("R2")
    ApiData<String> r2();

    @ApiOperation("R3")
    ApiData<String> r3();
}
