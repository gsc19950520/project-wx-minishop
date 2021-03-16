package com.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Component
public class Constant {

    @Autowired
    private Environment env;
    public static String appId;
    public static String appSecret;
    public static String token;


    @PostConstruct
    public void readConfig() {
        appId = env.getProperty("wx.appId");
        appSecret = env.getProperty("wx.appSecret");
        token = env.getProperty("wx.token");
    }
}
