package com.shop.controller;

import com.shop.bean.dto.UserInfoDto;
import com.shop.entity.WxUserInfo;
import com.shop.service.WxUserInfoService;
import com.shop.util.common.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

    /**
     * 获取当前线程请求的request对象
     * @return javax.servlet.http.HttpServletRequest
     * @date 2019/11/12
     */
    protected HttpServletRequest getCurrentRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    protected WxUserInfo getCurrentUserInfo(){
        return (WxUserInfo) SessionUtil.getSession(this.getCurrentRequest(), UserInfoDto.LOGIN_USER_INFO);
    }
}
