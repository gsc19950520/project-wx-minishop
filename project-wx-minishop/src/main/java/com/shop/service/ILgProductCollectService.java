package com.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.entity.LgProductCollect;
import com.shop.entity.WxUserInfo;
import com.shop.util.ServiceResult;

/**
 * <p>
 * 商品收藏 服务类
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
public interface ILgProductCollectService extends IService<LgProductCollect> {

    ServiceResult addCollect(WxUserInfo userInfo, String productId);

    ServiceResult cancelCollect(WxUserInfo userInfo, String productId);

    ServiceResult myCollects(WxUserInfo userInfo);

}
