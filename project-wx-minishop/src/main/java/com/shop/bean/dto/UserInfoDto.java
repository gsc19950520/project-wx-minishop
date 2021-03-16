package com.shop.bean.dto;

import lombok.Data;

@Data
public class UserInfoDto {

    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";

    private String openId;

    private String sessionKey;
}
