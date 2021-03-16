package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bean.dto.UserInfoDto;
import com.shop.bean.req.PreIndexReq;
import com.shop.entity.WxUserInfo;
import com.shop.enums.BusinessEnum;
import com.shop.mapper.WxUserInfoMapper;
import com.shop.service.WxUserInfoService;
import com.shop.util.CallWxUtil;
import com.shop.util.MyException;
import com.shop.util.ServiceResult;
import com.shop.util.common.SessionUtil;
import com.shop.util.common.UUIDGenerator;
import com.shop.util.token.CheckUtil;
import com.shop.util.token.CsrfTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 微信小程序登录信息表 服务实现类
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
@Slf4j
@Service
public class WxUserInfoServiceImpl extends ServiceImpl<WxUserInfoMapper, WxUserInfo> implements WxUserInfoService {

    @Resource
    private WxUserInfoMapper userInfoMapper;


    @Override
    public ServiceResult checkOpenId(HttpServletRequest request, PreIndexReq preIndexReq) {
        Assert.notNull(preIndexReq, BusinessEnum.ARGUMENT_VALID_ERROR.getValueInFact());
        ServiceResult result = ServiceResult.builder().build();
        UserInfoDto userInfoDto = CallWxUtil.code2session(preIndexReq.getCode());
        if(userInfoDto.getOpenId() != null){
//            if(CheckUtil.checkSessionKey(preIndexReq.getSignature(), preIndexReq.getRawData(), userInfoDto.getSessionKey())){
            QueryWrapper<WxUserInfo> userInfoWrapper = new QueryWrapper();
            userInfoWrapper.eq("openId", userInfoDto.getOpenId());
            WxUserInfo userInfo = userInfoMapper.selectOne(userInfoWrapper);
            String thirdSessionKey = UUIDGenerator.getUUID();
            if(userInfo == null){
                userInfo = new WxUserInfo();
                userInfo.setOpenid(userInfoDto.getOpenId());
            }
            userInfo.setThirdSessionKey(thirdSessionKey);
            userInfo.setLoginTime(new Date());
            userInfo.setLoginNumber((userInfo.getLoginNumber() != null ? userInfo.getLoginNumber() : 0) + 1);
            if(userInfo != null){
                userInfoMapper.updateById(userInfo);
            }else{
                userInfoMapper.insert(userInfo);
            }

            //redis设置thirdSessionKey

            result.addData("thirdSessionKey", thirdSessionKey);
            result.setSuccess(true);
            result.setMessage("免登成功");
            log.info(" 免登成功 openId:[{}], code:[{}]", userInfoDto.getOpenId(), preIndexReq.getCode());
            /*}else{
                log.info(" sessionKey校验失败 openId:[{}], signature:[{}], rawData:[{}]", userInfoDto.getOpenId(), preIndexReq.getSignature(), preIndexReq.getRawData());
                throw new MyException(BusinessEnum.SESSION_KEY_VALID_FAIL.getValue(), BusinessEnum.SESSION_KEY_VALID_FAIL.getValueInFact());
            }*/
        }else{
            log.info(" 免登失败 code:[{}]", preIndexReq.getCode());
            throw new MyException(BusinessEnum.LOGIN_FAIL.getValue(), BusinessEnum.LOGIN_FAIL.getValueInFact());
        }
        return result;
    }
}
