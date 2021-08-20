package com.example.ss.api.auth.feign;

import com.example.ss.api.auth.OauthApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * OAuth2 认证授权模块API客户端调用接口
 *
 * @author Aaric, created on 2021-08-20T15:40.
 * @version 0.10.0-SNAPSHOT
 */
@FeignClient(value = "ss-distributed-auth-backend")
public interface OauthApiFeign extends OauthApi {
}
