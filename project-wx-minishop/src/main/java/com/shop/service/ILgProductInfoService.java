package com.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bean.req.ProductReq;
import com.shop.entity.LgProductInfo;
import com.shop.util.ServiceResult;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
public interface ILgProductInfoService extends IService<LgProductInfo> {

    ServiceResult getIsPutProducts(ProductReq pr);

    ServiceResult getProductDetail(String productId);
}
