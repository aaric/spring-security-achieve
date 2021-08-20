package com.example.ss.api.test;

import com.example.ss.data.ApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试模块API接口
 *
 * @author Aaric, created on 2021-08-10T13:55.
 * @version 0.1.0-SNAPSHOT
 */
@Api(tags = "测试模块API")
public interface TestApi {

    @ApiOperation("OK")
    ApiData<String> ok();

    @ApiOperation("R1")
    ApiData<String> r1();

    @ApiOperation("R2")
    ApiData<String> r2();
}
