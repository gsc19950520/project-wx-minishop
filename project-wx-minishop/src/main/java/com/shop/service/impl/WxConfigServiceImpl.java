package com.shop.service.impl;

import com.shop.service.WxConfigService;
import com.shop.util.WxUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class WxConfigServiceImpl implements WxConfigService {

    @Override
    public String receive(HttpServletRequest request) {
        String res = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String timeStamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if(StringUtils.isNotBlank(signature)){
            try {
                if(WxUtils.checkSignature(signature, timeStamp, nonce)){
                    return res;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public String decodeUserInfo(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String encryptedData = request.getParameter("encryptedData");
        String rawData = request.getParameter("rawData");
        String iv = request.getParameter("iv");
        return null;
    }
}
