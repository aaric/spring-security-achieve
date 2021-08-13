# spring-security-achieve

[![license](https://img.shields.io/badge/license-MIT-green.svg?style=flat&logo=github)](https://www.mit-license.org)
[![gradle](https://img.shields.io/badge/gradle-7.1.1-brightgreen.svg?style=flat&logo=gradle)](https://docs.gradle.org/7.1/userguide/installation.html)
[![java](https://img.shields.io/badge/java-1.8-brightgreen.svg?style=flat&logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![spring boot](https://img.shields.io/badge/springboot-2.3.2-brightgreen.svg?style=flat&logo=springboot)](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/)
[![build](https://github.com/aaric/spring-security-achieve/workflows/build/badge.svg)](https://github.com/aaric/spring-security-achieve/actions)
[![release](https://img.shields.io/badge/release-0.7.0-blue.svg)](https://github.com/aaric/spring-security-achieve/releases)

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
    PRIMARY KEY (id) USING BTREE
) COMMENT = '用户角色表 测试';

-- authority table
CREATE TABLE base_authority(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    code VARCHAR(32) NOT NULL   COMMENT '编码' ,
    name VARCHAR(128)    COMMENT '名称' ,
    uri VARCHAR(128)    COMMENT 'URI' ,
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
    PRIMARY KEY (id) USING BTREE
) COMMENT = '角色权限表 测试';
```

## 3 Spring Security + OAuth2

### 3.1 Spring Boot For Gradle

```groovy
dependencies {
    implementation "org.springframework.boot:spring-boot-starter-security"
    implementation "org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure"
}
```

### 3.2 Spring Cloud For Gradle

```groovy
dependencies {
    implementation "org.springframework.cloud:spring-cloud-starter-security"
    implementation "org.springframework.cloud:spring-cloud-starter-oauth2"
}
```
