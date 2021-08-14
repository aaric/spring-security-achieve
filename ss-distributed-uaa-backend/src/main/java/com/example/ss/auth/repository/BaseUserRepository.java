package com.example.ss.auth.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ss.pojo.BaseUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息DAO接口
 *
 * @author Aaric, created on 2021-08-14T07:22.
 * @version 0.7.0-SNAPSHOT
 */
@Repository
public interface BaseUserRepository  extends BaseMapper<BaseUser> {

    /**
     * 查询用户拥有权限列表
     *
     * @param id 用户ID
     * @return
     */
    List<String> selectAuthorityList(@Param("id") Long id);
}
