package com.example.ss.auth.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * BaseUserRepositoryTests
 *
 * @author Aaric, created on 2021-08-14T07:27.
 * @version 0.7.0-SNAPSHOT
 */
@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BaseUserRepositoryTests {

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Test
    public void testSelectAuthorityList() throws Exception {
        List<String> authorityList = baseUserRepository.selectAuthorityList(1L);
        log.info("{}", authorityList);
        Assertions.assertNotNull(authorityList);
    }
}
