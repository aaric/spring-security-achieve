package com.example.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 服务网关应用
 *
 * @author Aaric, created on 2021-08-13T17:26.
 * @version 0.6.0-SNAPSHOT
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableZuulProxy
public class GatewayApp {

    /**
     * main
     *
     * @param args custom inputs
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
