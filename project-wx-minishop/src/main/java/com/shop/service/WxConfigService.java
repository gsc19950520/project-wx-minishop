package com.shop.service;

import javax.servlet.http.HttpServletRequest;

public interface WxConfigService {

    String receive(HttpServletRequest request);

    String decodeUserInfo(HttpServletRequest request);
}
