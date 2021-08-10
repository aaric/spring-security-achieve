package com.example.ss.api.test;

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
    String ok();

    @ApiOperation("R1")
    String r1();

    @ApiOperation("R2")
    String r2();
}
