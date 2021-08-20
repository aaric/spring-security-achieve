package com.example.ss.data;

import com.example.ss.core.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 请求API数据
 *
 * @param <T> 支持泛型
 * @author Aaric, created on 2021-08-20T14:30.
 * @version 0.8.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "请求API数据")
public class ApiData<T> {

    /**
     * 状态码：200-请求成功
     */
    @ApiModelProperty(position = 1, value = "状态码：200-请求成功")
    private Integer code = Constants.ApiCode.SUCCESS;

    /**
     * 错误信息
     */
    @ApiModelProperty(position = 2, value = "错误信息")
    private String errorMessage;

    /**
     * 数据结果
     */
    @ApiModelProperty(position = 3, value = "数据结果")
    private T data;
}
