package com.example.ss.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * BCryptTests
 *
 * @author Aaric, created on 2021-08-11T15:45.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
public class BCryptTests {

    @Test
    public void testCheck() {
        String salt = BCrypt.gensalt();
        String password = BCrypt.hashpw("admin", salt);
        log.debug("salt: {}, password: {}", salt, password);

        boolean flag = BCrypt.checkpw("admin", "$2a$10$GFxO0rEP3QUq/Tb92Re3P.60bjzo/XDmStAMvZuXJOsKghapaIuvS");
        log.debug("flag: {}", flag);
        Assertions.assertTrue(flag);
    }
}
