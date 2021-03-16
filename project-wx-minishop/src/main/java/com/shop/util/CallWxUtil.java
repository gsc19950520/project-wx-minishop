package com.shop.util;

import com.alibaba.fastjson.JSONObject;
import com.shop.bean.dto.UserInfoDto;
import com.shop.config.Constant;
import com.shop.util.httpclient.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CallWxUtil {

    /** 根据前端传值code获取当前授权用户的session信息 */
    private static final String CODE_TO_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=";

    public static UserInfoDto code2session(String js_code){
        UserInfoDto userInfoDto = new UserInfoDto();
        String requestUrl = CODE_TO_SESSION  + Constant.appId + "&secret=" + Constant.appSecret + "&grant_type=authorization_code" + "&js_code=" + js_code;
        try {
            String response = HttpUtil.getUrlAsStringHttps(requestUrl, null, null, "utf-8");
            if(StringUtils.isNotEmpty(response)) {
                JSONObject jsonObject = JSONObject.parseObject(response);
                String openId = jsonObject.getString("openid");
                if(openId != null){
                    userInfoDto.setOpenId(jsonObject.getString("openid"));
                    userInfoDto.setSessionKey(jsonObject.getString("session_key"));
                }else{
                    log.error("connect with wx occur error:[{}] " , jsonObject.getString("errmsg"));
                }
            }else{
                log.error("connect with wx response is null  ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoDto;
    }

}
