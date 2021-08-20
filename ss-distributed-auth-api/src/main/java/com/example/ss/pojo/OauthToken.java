package com.example.ss.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OAuth2 令牌信息
 *
 * @author Aaric, created on 2021-08-20T15:37.
 * @version 0.10.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "令牌信息")
public class OauthToken {

    @JsonProperty("access_token")
    @ApiModelProperty(position = 1, value = "令牌", example = "ef850905-5eac-4306-a691-189662114a80")
    private String accessToken;

    @JsonProperty("token_type")
    @ApiModelProperty(position = 2, value = "令牌格式", example = "bearer")
    private String tokenType;

    @JsonProperty("refresh_token")
    @ApiModelProperty(position = 3, value = "刷新令牌", example = "649ce89a-f240-4230-b1f9-21a84296639a")
    private String refreshToken;

    @JsonProperty("expires_in")
    @ApiModelProperty(position = 4, value = "令牌过期秒", example = "33045")
    private Integer expiresIn;

    @JsonProperty("scope")
    @ApiModelProperty(position = 5, value = "令牌授权范围", example = "all")
    private String scope;

    @JsonProperty("error")
    @ApiModelProperty(position = 6, value = "错误码", example = "invalid_token")
    private String error;

    @JsonProperty("error_description")
    @ApiModelProperty(position = 7, value = "错误描述", example = "Invalid access token: token")
    private String errorDescription;
}
