package com.example.ss.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testAuthorities() {
        // 定义父级权限映射
        Map<String, String> refs = new HashMap<>();
        refs.put("R0100", "message");

        // 定义数字01权限模板
        Map<String, String> privilegeTemplates = new HashMap<>();
        privilegeTemplates.put("message", "0000");

        // 查询拥有的权限
        List<String> authorities = Arrays.asList("R0101", "R0104");

        // 替换模板
        authorities.forEach(authority -> {
            String refKey = authority.substring(0, 3) + "00";
            int authorityIndex = Integer.valueOf(authority.substring(3));
            log.info("{}, {}", refKey, authorityIndex);

            String privilegeKey = refs.get(refKey);
            char[] chars = privilegeTemplates.get(privilegeKey).toCharArray();
            chars[authorityIndex - 1] = '1';
            privilegeTemplates.put(privilegeKey, new String(chars));
        });

        // 结果
        Assertions.assertEquals("1001", privilegeTemplates.get("message"));
    }
}
