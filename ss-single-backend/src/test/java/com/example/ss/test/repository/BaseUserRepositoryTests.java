package com.example.ss.test.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * BaseUserRepositoryTests
 *
 * @author Aaric, created on 2021-08-11T16:29.
 * @version 0.4.0-SNAPSHOT
 */
//@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BaseUserRepositoryTests {

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Test
    public void testTotalRecord() throws Exception {
        log.info("totalRecord: {}", baseUserRepository.totalRecord());
    }
}
