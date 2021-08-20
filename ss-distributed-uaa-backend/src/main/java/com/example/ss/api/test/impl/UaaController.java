package com.example.ss.api.test.impl;

import com.example.ss.api.test.UaaApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试UAA模块API接口控制器
 *
 * @author Aaric, created on 2021-08-13T13:56.
 * @version 0.6.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/uaa")
public class UaaController implements UaaApi {

    @Value("${uaa.data-id}")
    private String dataId;

    @Override
    @GetMapping("/getDataId")
    public String getDataId() {
        return "dataId: " + dataId;
    }
}
