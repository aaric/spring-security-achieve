package com.example.ss.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试模块API接口
 *
 * @author Aaric, created on 2021-08-13T13:55.
 * @version 0.6.0-SNAPSHOT
 */
@Api(tags = "测试模块API")
public interface UaaApi {

    @ApiOperation("获取DataId")
    String getDataId();
}
