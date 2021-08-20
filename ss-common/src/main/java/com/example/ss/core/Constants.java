package com.example.ss.core;

/**
 * 常量类
 *
 * @author Aaric, created on 2021-08-20T14:29.
 * @version 0.8.0-SNAPSHOT
 */
public interface Constants {

    /**
     * 状态码
     */
    interface ApiCode {

        /**
         * 响应码：200-请求成功
         */
        int SUCCESS = 200;

        /**
         * 响应码：400-数据校验异常
         */
        int ERROR_400 = 400;

        /**
         * 响应码：400-权限校验错误
         */
        int ERROR_401 = 401;

        /**
         * 响应码：500-其他异常
         */
        int ERROR_500 = 500;

        /**
         * 响应码：503-无法解决异常
         */
        int ERROR_503 = 503;

    }
}
