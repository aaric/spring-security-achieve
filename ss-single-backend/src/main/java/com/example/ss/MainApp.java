package com.example.ss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring boot launcher.
 *
 * @author Aaric, created on 2021-07-30T11:12.
 * @version 0.1.0-SNAPSHOT
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.ss.*.repository")
public class MainApp {

    /**
     * main
     *
     * @param args custom inputs
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
