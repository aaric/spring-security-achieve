package com.example.ss.api.test.impl;

import com.example.ss.api.test.BaseApi;
import com.example.ss.data.ApiData;
import com.example.ss.security.pojo.UserExtend;
import com.example.ss.util.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 测试基础支撑模块API接口控制器
 *
 * @author Aaric, created on 2021-08-13T13:56.
 * @version 0.8.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/order")
public class BaseController implements BaseApi {

    @Value("${base.data-id}")
    private String dataId;

    @Autowired
    private TokenStore tokenStore;

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserExtend) {
            UserExtend userExtend = (UserExtend) principal;
            return userExtend.getUsername();
        }
        return null;
    }

    @Override
    @GetMapping("/getDataId")
    public ApiData<String> getDataId() {
        Collection<OAuth2AccessToken> oAuth2AccessTokens = tokenStore.findTokensByClientIdAndUserName("client", "admin");
        oAuth2AccessTokens.forEach(token -> log.info("{}", token.getValue()));
        return new ApiData<String>()
                .setData("dataId: " + dataId);
    }

    @Override
    @PutMapping("/updateGrantedAuthorities")
    public ApiData<String> updateGrantedAuthorities(@RequestParam Long newUserId, @RequestBody String[] newAuthorities) {
        // 获取访问令牌
        String accessToken = HttpServletUtils.getRequest().getHeader("Authorization").replaceFirst("Bearer ", "");
        log.info("accessToken={}, newAuthorities={}", accessToken, newAuthorities);

        // 获取权限列表
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("grantedAuthorities: {}", authentication.getAuthorities());

        // 更新用户信息与权限列表
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserExtend) {
            // 获取用户ID
            UserExtend userExtend = (UserExtend) principal;
            log.info("userId: {}", userExtend.getId());

            // 设置用户ID
            userExtend.setId(newUserId);

            // 初始化权限列表
            List<GrantedAuthority> newGrantedAuthorities = AuthorityUtils.createAuthorityList(newAuthorities);
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userExtend, authentication.getCredentials(), newGrantedAuthorities);

            // 更新RAM（非必要）
            /*SecurityContextHolder.getContext().setAuthentication(newAuthentication);*/

            // 设置用户认证信息
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
            OAuth2Request auth2Request = oAuth2Authentication.getOAuth2Request();
            OAuth2Authentication newOAuth2Authentication = new OAuth2Authentication(auth2Request, newAuthentication);

            // 更新Redis
            tokenStore.storeAccessToken(oAuth2AccessToken, newOAuth2Authentication);
        }

        return new ApiData<String>()
                .setData(getUsername() + " authorities updated");
    }

    @Override
    @GetMapping("/r1")
    @PreAuthorize("hasAuthority('a1')")
    public ApiData<String> r1() {
        return new ApiData<String>()
                .setData(getUsername() + " visit r1");
    }

    @Override
    @GetMapping("/r2")
    @PreAuthorize("hasAuthority('a2')")
    public ApiData<String> r2() {
        return new ApiData<String>()
                .setData(getUsername() + " visit r2");
    }

    @Override
    @GetMapping("/r3")
    @PreAuthorize("hasAuthority('a3')")
    public ApiData<String> r3() {
        return new ApiData<String>()
                .setData(getUsername() + " visit r3");
    }
}
