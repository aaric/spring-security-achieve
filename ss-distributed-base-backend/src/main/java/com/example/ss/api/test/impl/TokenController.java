package com.example.ss.api.test.impl;

import com.example.ss.api.auth.feign.OauthApiFeign;
import com.example.ss.api.test.TokenApi;
import com.example.ss.data.ApiData;
import com.example.ss.data.ApiException;
import com.example.ss.pojo.OauthCheckToken;
import com.example.ss.pojo.OauthToken;
import com.example.ss.security.UserDetailsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试令牌模块API接口控制器
 *
 * @author Aaric, created on 2021-08-20T15:48.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/token")
public class TokenController implements TokenApi {

    @Autowired
    private OauthApiFeign oauthApiFeign;

    @Override
    @PostMapping("/login")
    public ApiData<OauthToken> login(@RequestParam String account,
                                     @RequestParam String passwd) throws ApiException {
        // 获取令牌
        OauthToken oauthToken = oauthApiFeign.token("client", "secret", "password",
                null, "admin", "admin", null);
        log.info("oauthToken: {}", oauthToken);

        return new ApiData<OauthToken>()
                .setData(oauthToken);
    }

    @Override
    @PostMapping("/valid")
    public ApiData<OauthCheckToken> valid(@RequestParam String accessToken) throws ApiException {
        // 校验令牌
        OauthCheckToken oauthCheckToken = oauthApiFeign.checkToken(accessToken);
        log.info("oauthCheckToken: {}", oauthCheckToken);

        return new ApiData<OauthCheckToken>()
                .setData(oauthCheckToken);
    }

    @Override
    @PostMapping("/refresh")
    public ApiData<OauthToken> refresh(@RequestParam String refreshToken) throws ApiException {
        // 刷新令牌
        OauthToken oauthToken = oauthApiFeign.token("client", "secret", "refresh_token",
                null, null, null, refreshToken);
        log.info("oauthToken: {}", oauthToken);

        return new ApiData<OauthToken>()
                .setData(oauthToken);
    }

    @Override
    @PostMapping("/current")
    public ApiData<String> current() throws ApiException {
        // 当前用户信息
        Long userId = UserDetailsUtil.getUserId();
        String username = UserDetailsUtil.getUsername();
        log.info("current username: {}", username);

        return new ApiData<String>()
                .setData(String.format("%d -> %s", userId, username));
    }
}
