package com.shop.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.shop.bean.req.ProductReq;
import com.shop.entity.WxUserInfo;
import com.shop.enums.BusinessEnum;
import com.shop.service.ILgProductCollectService;
import com.shop.util.MyException;
import com.shop.util.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 商品收藏 前端控制器
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
@Slf4j
@Controller
@RequestMapping("/lg-product-collect")
public class LgProductCollectController extends BaseController {

    @Resource
    private ILgProductCollectService productCollectService;

    /**
     * 收藏
     * @param pr
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/addCollect")
    public ServiceResult addCollect(@RequestBody ProductReq pr) {
        WxUserInfo userInfo = this.getCurrentUserInfo();
        if(userInfo == null){
            throw new MyException(BusinessEnum.GET_CURRENT_USER_INFO_ERROR);
        }
        return this.productCollectService.addCollect(userInfo, pr.getProductId());
    }

    /**
     * 取消收藏
     * @param pr
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/cancelCollect")
    public ServiceResult cancelCollect(@RequestBody ProductReq pr) {
        WxUserInfo userInfo = this.getCurrentUserInfo();
        if(userInfo == null){
            throw new MyException(BusinessEnum.GET_CURRENT_USER_INFO_ERROR);
        }
        return this.productCollectService.cancelCollect(userInfo, pr.getProductId());
    }


    /**
     * 我的收藏列表
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/myCollects")
    public ServiceResult myCollects() {
        WxUserInfo userInfo = this.getCurrentUserInfo();
        if(userInfo == null){
            throw new MyException(BusinessEnum.GET_CURRENT_USER_INFO_ERROR);
        }
        return this.productCollectService.myCollects(userInfo);
    }
}
