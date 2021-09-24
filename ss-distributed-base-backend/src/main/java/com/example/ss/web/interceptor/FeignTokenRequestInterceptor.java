package com.example.ss.web.interceptor;

import com.example.ss.util.HttpServletUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Feign 请求Token拦截器
 *
 * @author Aaric, created on 2021-09-24T10:00.
 * @version 0.13.0-SNAPSHOT
 */
public class FeignTokenRequestInterceptor implements RequestInterceptor {

    private static final String HEADER_AUTHORIZATION_KEY = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        template.header(HEADER_AUTHORIZATION_KEY, HttpServletUtils.getRequest().getHeader(HEADER_AUTHORIZATION_KEY));
    }
}
