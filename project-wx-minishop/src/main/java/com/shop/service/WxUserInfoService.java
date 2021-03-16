package com.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bean.req.PreIndexReq;
import com.shop.entity.WxUserInfo;
import com.shop.util.ServiceResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 微信小程序登录信息表 服务类
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
public interface WxUserInfoService extends IService<WxUserInfo> {

    /**
     * 修改密码发送手机验证码
     *
     * @param preIndexReq
     * @return
     */
    ServiceResult checkOpenId(HttpServletRequest request, PreIndexReq preIndexReq);
}
