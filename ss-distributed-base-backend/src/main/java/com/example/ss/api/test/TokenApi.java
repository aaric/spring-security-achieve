package com.example.ss.api.test;

import com.example.ss.data.ApiData;
import com.example.ss.data.ApiException;
import com.example.ss.pojo.OauthCheckToken;
import com.example.ss.pojo.OauthToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 测试令牌模块API接口
 *
 * @author Aaric, created on 2021-08-20T15:48.
 * @version 0.10.0-SNAPSHOT
 */
@Api(tags = "测试令牌模块API")
public interface TokenApi {

    @ApiOperation("登录获取令牌")
    ApiData<OauthToken> login(@ApiParam(value = "账户名", example = "admin") String account,
                              @ApiParam(value = "账户密码", example = "admin") String passwd) throws ApiException;

    @ApiOperation("校验令牌")
    ApiData<OauthCheckToken> valid(@ApiParam(value = "访问令牌", example = "access_token") String accessToken) throws ApiException;

    @ApiOperation("刷新令牌")
    ApiData<OauthToken> refresh(@ApiParam(value = "刷新令牌", example = "refresh_token") String refreshToken) throws ApiException;

    @ApiOperation("当前登录用户信息")
    ApiData<String> current() throws ApiException;
}
