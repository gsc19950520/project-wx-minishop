package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bean.req.ProductReq;
import com.shop.entity.LgProductInfo;
import com.shop.mapper.LgProductInfoMapper;
import com.shop.service.ILgProductInfoService;
import com.shop.util.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
@Slf4j
@Service
public class LgProductInfoServiceImpl extends ServiceImpl<LgProductInfoMapper, LgProductInfo> implements ILgProductInfoService {

    @Resource
    private LgProductInfoMapper productInfoMapper;

    @Override
    public ServiceResult getIsPutProducts(ProductReq pr) {
        ServiceResult result = ServiceResult.builder().success(true).message("获取商品列表成功").build();
        QueryWrapper<LgProductInfo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(pr.getKeyWord())){
            queryWrapper.like("productName", pr.getKeyWord());
        }
        Page<LgProductInfo> page = productInfoMapper.selectPage(new Page<>(pr.getPageNo(), pr.getPageSize()), queryWrapper);
        result.addData("products", page.getRecords());
        return result;
    }

    @Override
    public ServiceResult getProductDetail(String productId) {
        ServiceResult result = ServiceResult.builder().success(true).message("获取商品详情成功").build();
        QueryWrapper<LgProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productId", productId);
        LgProductInfo productInfo = productInfoMapper.selectOne(queryWrapper);
        result.addData("productInfo", productInfo);
        return result;
    }
}
