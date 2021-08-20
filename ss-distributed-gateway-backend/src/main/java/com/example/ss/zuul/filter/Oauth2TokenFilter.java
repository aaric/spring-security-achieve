package com.example.ss.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * OAuth2令牌 过滤器
 *
 * @author Aaric, created on 2021-08-20T15:25.
 * @version 0.9.0-SNAPSHOT
 */
@Slf4j
public class Oauth2TokenFilter extends ZuulFilter {

    /**
     * Bearer 令牌Header Key
     */
    private static final String OAUTH2_AUTHORIZATION_KEY = "Authorization";

    /**
     * 支持的过滤器类型：
     * <u>
     * <li>pre-路由前</li>
     * <li>route-路由时</li>
     * <li>post-路由完毕</li>
     * <li>error-发生错误时</li>
     * </u>
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String token = request.getHeader(OAUTH2_AUTHORIZATION_KEY);
        if (StringUtils.isNotBlank(token)) {
            log.info("token: {}={}", OAUTH2_AUTHORIZATION_KEY, token);
            context.addZuulRequestHeader(OAUTH2_AUTHORIZATION_KEY, token);
        }

        return null;
    }
}
