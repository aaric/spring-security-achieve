package com.example.ss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 基础支撑应用
 *
 * @author Aaric, created on 2021-08-13T17:15.
 * @version 0.8.0-SNAPSHOT
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.ss.*.repository")
@EnableDiscoveryClient
@RefreshScope
@EnableFeignClients
@EnableHystrix
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BaseApp {

    /**
     * main
     *
     * @param args custom inputs
     */
    public static void main(String[] args) {
        SpringApplication.run(BaseApp.class, args);
    }
}
