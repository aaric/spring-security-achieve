# spring-security-achieve

[![license](https://img.shields.io/badge/license-MIT-green.svg?style=flat&logo=github)](https://www.mit-license.org)
[![gradle](https://img.shields.io/badge/gradle-7.1.1-brightgreen.svg?style=flat&logo=gradle)](https://docs.gradle.org/7.1/userguide/installation.html)
[![java](https://img.shields.io/badge/java-1.8-brightgreen.svg?style=flat&logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![spring boot](https://img.shields.io/badge/springboot-2.3.2-brightgreen.svg?style=flat&logo=springboot)](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/)
[![build](https://github.com/aaric/spring-security-achieve/workflows/build/badge.svg)](https://github.com/aaric/spring-security-achieve/actions)
[![release](https://img.shields.io/badge/release-0.11.0-blue.svg)](https://github.com/aaric/spring-security-achieve/releases)

Spring Security Learning.

## 1 Create Database

```mysql
-- create database
CREATE DATABASE testdb DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;

CREATE USER 'testdb'@'%' IDENTIFIED WITH mysql_native_password BY 'testdb';
GRANT ALL ON testdb.* TO 'testdb'@'%';

FLUSH PRIVILEGES;

SHOW GRANTS FOR 'testdb'@'%';0
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
    PRIMARY KEY (id) USING BTREE
) COMMENT = '用户表 测试';

-- role table
CREATE TABLE base_role(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    code VARCHAR(32) NOT NULL   COMMENT '编码' ,
    name VARCHAR(128) NOT NULL   COMMENT '名称' ,
    remark VARCHAR(1024)    COMMENT '备注' ,
    is_deleted INT NOT NULL  DEFAULT 0 COMMENT '是否删除：0-否，1-是' ,
    created_by BIGINT    COMMENT '创建人' ,
    created_at DATETIME    COMMENT '创建时间' ,
    updated_by BIGINT    COMMENT '更新人' ,
    updated_at DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id) USING BTREE
) COMMENT = '角色表 测试';

-- user-role ref table
CREATE TABLE base_user_role(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    user_id BIGINT NOT NULL   COMMENT '用户ID' ,
    role_id BIGINT NOT NULL   COMMENT '角色ID' ,
    is_deleted INT NOT NULL  DEFAULT 0 COMMENT '是否删除：0-否，1-是' ,
    created_by BIGINT    COMMENT '创建人' ,
    created_at DATETIME    COMMENT '创建时间' ,
    PRIMARY KEY (id) USING BTREE ,
    FOREIGN KEY (`user_id`) REFERENCES `base_user` (`id`) ,
    FOREIGN KEY (`role_id`) REFERENCES `base_role` (`id`)
) COMMENT = '用户角色表 测试';

-- authority table
CREATE TABLE base_authority(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    code VARCHAR(32) NOT NULL   COMMENT '编码' ,
    name VARCHAR(128)    COMMENT '资源名称' ,
    http_method VARCHAR(32)    COMMENT '请求方法' ,
    http_url VARCHAR(1024)    COMMENT '请求路径' ,
    remark VARCHAR(1024)    COMMENT '备注' ,
    is_deleted INT NOT NULL  DEFAULT 0 COMMENT '是否删除：0-否，1-是' ,
    created_by BIGINT    COMMENT '创建人' ,
    created_at DATETIME    COMMENT '创建时间' ,
    updated_by BIGINT    COMMENT '更新人' ,
    updated_at DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id) USING BTREE
) COMMENT = '权限表 测试';

-- role-authority ref table
CREATE TABLE base_role_authority(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    role_id BIGINT NOT NULL   COMMENT '角色ID' ,
    authority_id INT NOT NULL   COMMENT '权限ID' ,
    is_deleted INT NOT NULL  DEFAULT 0 COMMENT '是否删除：0-否，1-是' ,
    created_by BIGINT    COMMENT '创建人' ,
    created_at DATETIME    COMMENT '创建时间' ,
    PRIMARY KEY (id) USING BTREE ,
    FOREIGN KEY (`role_id`) REFERENCES `base_role` (`id`) ,
    FOREIGN KEY (`authority_id`) REFERENCES `base_authority` (`id`)
) COMMENT = '角色权限表 测试';
```

## 3 Spring Security + OAuth2

> [oauth2-schema.sql](https://github.com/spring-projects/spring-security-oauth/blob/2.3.4.RELEASE/spring-security-oauth2/src/test/resources/schema.sql)

### 3.1 MySQL Table

|No.|客户端ID|客户端密钥|授权资源IDs|授权范围|统一认证回调地址|访问令牌过期秒|刷新令牌过期秒|备注|
|:-:|:-:|---|:-:|:-:|---|:-:|:-:|---|
|1|client|secret|test|all|http://example.com|12h|1d|`仅限测试使用`|

```mysql
-- create oauth2 table
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '客户端的资源ID集合（英文逗号分隔）',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端密钥',
  `scope` varchar(256) DEFAULT NULL COMMENT '客户端的授权范围（英文逗号分隔）',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '客户端支持的授权类型（英文逗号分隔）',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '客户端的统一认证回调URI，仅限authorization_code或implicit类型',
  `authorities` varchar(256) DEFAULT NULL COMMENT '客户端的权限集合（英文逗号分隔）',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '客户端的访问令牌过期秒',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '客户端的刷新令牌过期秒',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '预留字段（必须为JSON数据格式）',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '设置用户是否需求授权操作，默认值为false',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='OAuth2客户端详情表';

INSERT INTO `oauth_client_details`
(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `access_token_validity`, `refresh_token_validity`, `autoapprove`)
VALUES
('client', 'base', '$2a$10$AAQ0cD25u0brKc0d3F7mpe8Yg1YODZaQHR9xHxeae.0Mf0NgISmlK', 'all', 'authorization_code,implicit,password,client_credentials,refresh_token', 'http://example.com', 43200, 86400, NULL);
```

### 3.2 Spring Boot For Gradle

```groovy
dependencies {
    implementation "org.springframework.boot:spring-boot-starter-security"
    implementation "org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure"
}
```

### 3.3 Spring Cloud For Gradle

```groovy
dependencies {
    implementation "org.springframework.cloud:spring-cloud-starter-security"
    implementation "org.springframework.cloud:spring-cloud-starter-oauth2"
}
```
