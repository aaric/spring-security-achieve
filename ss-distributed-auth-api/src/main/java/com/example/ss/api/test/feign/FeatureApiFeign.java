package com.example.ss.api.test.feign;

import com.example.ss.api.test.FeatureApi;
import com.example.ss.api.test.fallback.FeatureApiFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 测试微服务特性API接口调用客户端
 *
 * @author Aaric, created on 2021-09-09T16:19.
 * @version 0.12.0-SNAPSHOT
 */
@FeignClient(value = "ss-distributed-auth-backend", fallback = FeatureApiFallback.class)
public interface FeatureApiFeign extends FeatureApi {
}
