package com.example.ss.test.repository;

import com.example.ss.pojo.BaseUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

/**
 * BaseUserRepositoryTests
 *
 * @author Aaric, created on 2021-08-11T16:29.
 * @version 0.4.0-SNAPSHOT
 */
@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BaseUserRepositoryTests {

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Test
    public void testInsert() throws Exception {
        String salt = BCrypt.gensalt();
        String password = BCrypt.hashpw("admin", salt);
        BaseUser baseUser = new BaseUser()
                .setUsername("admin")
                .setPasswd(password)
                .setPasswdSalt(salt)
                .setCreatedAt(new Date());
        baseUserRepository.insert(baseUser);
    }

    @Test
    public void testSelectAuthorityList() throws Exception {
        List<String> authorityList = baseUserRepository.selectAuthorityList(1L);
        log.info("{}", authorityList);
        Assertions.assertNotNull(authorityList);
    }
}
