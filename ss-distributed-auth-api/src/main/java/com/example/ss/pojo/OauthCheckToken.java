package com.example.ss.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OAuth2 令牌校验信息
 *
 * @author Aaric, created on 2021-08-20T15:38.
 * @version 0.10.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "令牌校验信息")
public class OauthCheckToken {

    @JsonProperty("aud")
    @ApiModelProperty(position = 1, value = "授权资源IDs", example = "[\"payment\"]")
    private String[] aud;

    @JsonProperty("user_name")
    @ApiModelProperty(position = 2, value = "账户名", example = "admin")
    private String userName;

    @JsonProperty("scope")
    @ApiModelProperty(position = 3, value = "授权范围", example = "[\"all\"]")
    private String[] scope;

    @JsonProperty("active")
    @ApiModelProperty(position = 4, value = "激活状态", example = "true")
    private Boolean active;

    @JsonProperty("exp")
    @ApiModelProperty(position = 5, value = "令牌过期时间秒", example = "1629460800")
    private Long exp;

    @JsonProperty("authorities")
    @ApiModelProperty(position = 6, value = "权限IDs", example = "[\"a1\",\"a2\"]")
    private String[] authorities;

    @JsonProperty("client_id")
    @ApiModelProperty(position = 7, value = "客户端ID", example = "client")
    private String clientId;

    @JsonProperty("error")
    @ApiModelProperty(position = 8, value = "错误码", example = "invalid_token")
    private String error;

    @JsonProperty("error_description")
    @ApiModelProperty(position = 9, value = "错误描述", example = "Invalid access token: token")
    private String errorDescription;
}
