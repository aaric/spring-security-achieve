package com.example.ss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 认证授权应用
 *
 * @author Aaric, created on 2021-08-13T17:00.
 * @version 0.8.0-SNAPSHOT
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.ss.*.repository")
@EnableDiscoveryClient
@RefreshScope
public class AuthApp {

    /**
     * main
     *
     * @param args custom inputs
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }
}
