package com.example.ss.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户信息
 *
 * @author Aaric, created on 2021-08-11T16:14.
 * @version 0.4.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户信息")
public class BaseUser {

    @ApiModelProperty(position = 1, value = "ID", example = "1L")
    private Long id;

    @ApiModelProperty(position = 1, value = "用户名", example = "admin")
    private String username;

    @ApiModelProperty(position = 1, value = "密码", example = "secret")
    private String passwd;

    @ApiModelProperty(position = 1, value = "密码盐", example = "abc")
    private String passwdSalt;

    @ApiModelProperty(position = 1, value = "是否删除：0-否，1-是")
    private Integer isDeleted;

    @ApiModelProperty(position = 1, value = "创建人")
    private Long createdBy;

    @ApiModelProperty(position = 1, value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(position = 1, value = "更新人")
    private Long updatedBy;

    @ApiModelProperty(position = 1, value = "更新时间")
    private Date updatedAt;
}
