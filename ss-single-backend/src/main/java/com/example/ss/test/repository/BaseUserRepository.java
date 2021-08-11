package com.example.ss.test.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ss.pojo.BaseUser;
import org.springframework.stereotype.Repository;

/**
 * hello title
 *
 * @author Aaric, created on 2021-08-11T16:25.
 * @version 0.4.0-SNAPSHOT
 */
@Repository
public interface BaseUserRepository extends BaseMapper<BaseUser> {

    /**
     * 获取记录总数
     *
     * @return
     */
    Long totalRecord();
}
