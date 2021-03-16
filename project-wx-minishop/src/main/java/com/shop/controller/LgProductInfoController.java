package com.shop.controller;

import com.shop.bean.req.ProductReq;
import com.shop.service.ILgProductInfoService;
import com.shop.util.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
@Slf4j
@Controller
@RequestMapping("/lg-product-info")
public class LgProductInfoController extends BaseController {

    @Resource
    private ILgProductInfoService productInfoService;

    /**
     * 获取商品列表信息
     * @param pr
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/productList")
    public ServiceResult productList(@RequestBody ProductReq pr) {
        return this.productInfoService.getIsPutProducts(pr);
    }

    /**
     * 获取商品详情信息
     * @param pr
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/productDetail")
    public ServiceResult productDetail(@RequestBody ProductReq pr) {
        return this.productInfoService.getProductDetail(pr.getProductId());
    }
}
