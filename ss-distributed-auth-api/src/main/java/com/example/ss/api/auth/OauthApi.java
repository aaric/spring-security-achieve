package com.example.ss.api.auth;

import com.example.ss.pojo.OauthCheckToken;
import com.example.ss.pojo.OauthToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OAuth2 认证授权模块API<br>
 * <ul>
 *     <li>authorization_code -> POST {{base_url}}/oauth/token?client_id={{client_id}}&client_secret={{client_secret}}&grant_type=authorization_code&code={{code}}&redirect_uri={{redirect_uri}}</li>
 *     <li>password -> POST {{base_url}}/oauth/token?client_id={{client_id}}&client_secret={{client_secret}}&grant_type=password&username={{username}}&password={{password}}</li>
 *     <li>refresh_token -> POST {{base_url}}/oauth/token?client_id={{client_id}}&client_secret={{client_secret}}&grant_type=refresh_token&refresh_token={{refresh_token}}</li>
 * </ul>
 *
 * @author Aaric, created on 2021-08-20T15:36.
 * @version 0.10.0-SNAPSHOT
 */
@Api(tags = "OAuth2认证授权模块API")
public interface OauthApi {

    @ApiOperation("获取或刷新令牌")
    @PostMapping("/oauth/token")
    OauthToken token(@ApiParam(name = "client_id", value = "客户端ID") @RequestParam("client_id") String clientId,
                     @ApiParam(name = "client_secret", value = "客户端密钥") @RequestParam("client_secret") String clientSecret,
                     @ApiParam(name = "grant_type", value = "授权类型：authorization_code, password, refresh_token") @RequestParam("grant_type") String grantType,
                     @ApiParam(name = "redirect_uri", value = "SSO回调地址（授权码模式）") @RequestParam(required = false, name = "redirect_uri") String redirectUri,
                     @ApiParam(name = "username", value = "账户名（密码模式）") @RequestParam(required = false, name = "username") String username,
                     @ApiParam(name = "password", value = "账户密码（密码模式）") @RequestParam(required = false, name = "password") String password,
                     @ApiParam(name = "refresh_token", value = "账户密码（刷新令牌模式）") @RequestParam(required = false, name = "refresh_token") String refreshToken);

    @ApiOperation("校验令牌")
    @PostMapping("/oauth/check_token")
    OauthCheckToken checkToken(@ApiParam(name = "token", value = "授权令牌") @RequestParam("token") String token);
}
