# spring-security-achieve

[![license](https://img.shields.io/badge/license-MIT-green.svg?style=flat&logo=github)](https://www.mit-license.org)
[![gradle](https://img.shields.io/badge/gradle-7.1.1-brightgreen.svg?style=flat&logo=gradle)](https://docs.gradle.org/7.1/userguide/installation.html)
[![java](https://img.shields.io/badge/java-1.8-brightgreen.svg?style=flat&logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![spring boot](https://img.shields.io/badge/springboot-2.3.2-brightgreen.svg?style=flat&logo=springboot)](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/)
[![build](https://github.com/aaric/spring-security-achieve/workflows/build/badge.svg)](https://github.com/aaric/spring-security-achieve/actions)
[![release](https://img.shields.io/badge/release-0.4.0-blue.svg)](https://github.com/aaric/spring-security-achieve/releases)

Spring Security Learning.

## 1 Create Database

```mysql
-- create database
CREATE DATABASE testdb DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;

CREATE USER 'testdb'@'%' IDENTIFIED WITH mysql_native_password BY 'testdb';
GRANT ALL ON testdb.* TO 'testdb'@'%';

FLUSH PRIVILEGES;

SHOW GRANTS FOR 'testdb'@'%';
```

## 2 Create Tables

```mysql
-- user table
CREATE TABLE base_user(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    username VARCHAR(64)    COMMENT '用户名' ,
    passwd VARCHAR(128)    COMMENT '密码' ,
    passwd_salt VARCHAR(32)    COMMENT '密码盐' ,
    is_deleted INT NOT NULL  DEFAULT 0 COMMENT '是否删除：0-否，1-是' ,
    created_by BIGINT    COMMENT '创建人' ,
    created_at DATETIME    COMMENT '创建时间' ,
    updated_by BIGINT    COMMENT '更新人' ,
    updated_at DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '用户表 测试';
```
