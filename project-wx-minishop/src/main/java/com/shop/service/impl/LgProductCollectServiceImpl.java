package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.entity.LgProductCollect;
import com.shop.entity.LgProductInfo;
import com.shop.entity.WxUserInfo;
import com.shop.enums.BusinessEnum;
import com.shop.mapper.LgProductCollectMapper;
import com.shop.service.ILgProductCollectService;
import com.shop.service.ILgProductInfoService;
import com.shop.util.MyException;
import com.shop.util.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 商品收藏 服务实现类
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
@Slf4j
@Service
public class LgProductCollectServiceImpl extends ServiceImpl<LgProductCollectMapper, LgProductCollect> implements ILgProductCollectService {

    @Resource
    private LgProductCollectMapper productCollectMapper;
    @Resource
    private ILgProductInfoService productInfoService;

    @Override
    public ServiceResult addCollect(WxUserInfo userInfo, String productId) {
        ServiceResult result = ServiceResult.builder().success(true).message("收藏成功").build();
        QueryWrapper<LgProductCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openId", userInfo.getOpenid());
        queryWrapper.eq("productId", productId);
        LgProductCollect productCollect = productCollectMapper.selectOne(queryWrapper);
        if(productCollect != null){
            throw new MyException(BusinessEnum.REPEAT_COLLECT);
        }
        LgProductCollect collect = LgProductCollect.builder()
                .openId(userInfo.getOpenid())
                .productId(productId)
                .createTime(new Date()).build();
        if(productCollectMapper.insert(collect) != 1){
            throw new MyException(BusinessEnum.ERROR);
        }
        return result;
    }

    @Override
    public ServiceResult cancelCollect(WxUserInfo userInfo, String productId) {
        ServiceResult result = ServiceResult.builder().success(true).message("取消收藏成功").build();
        QueryWrapper<LgProductCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openId", userInfo.getOpenid());
        queryWrapper.eq("productId", productId);
        LgProductCollect productCollect = productCollectMapper.selectOne(queryWrapper);
        if(productCollect == null){
            throw new MyException(BusinessEnum.HAVE_NOT_COLLECT);
        }
        if(productCollectMapper.deleteById(productCollect.getId()) != 1){
            throw new MyException(BusinessEnum.ERROR);
        }
        return result;
    }

    @Override
    public ServiceResult myCollects(WxUserInfo userInfo) {
        ServiceResult result = ServiceResult.builder().success(true).message("获取收藏列表成功").build();
        QueryWrapper<LgProductCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openId", userInfo.getOpenid());
        List<LgProductCollect> productCollects = productCollectMapper.selectList(queryWrapper);
        List<LgProductInfo> productInfos = new LinkedList<>();
        for(LgProductCollect collect : productCollects){
            String productId = collect.getProductId();
            ServiceResult productDetail = productInfoService.getProductDetail(productId);
            if(productDetail.getSuccess()){
                productInfos.add((LgProductInfo) productDetail.getData().get("productInfo"));
            }
        }
        result.addData("productInfos", productInfos);
        return result;
    }
}
