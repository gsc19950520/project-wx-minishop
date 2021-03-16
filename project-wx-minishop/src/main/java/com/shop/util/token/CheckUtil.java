package com.shop.util.token;

import com.shop.util.WxGlobalConstants;
import com.shop.util.crypt.aes.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.web.servlet.support.WebContentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Slf4j
public class CheckUtil {

    public static boolean checkCsrfToken(HttpServletRequest request, HttpServletResponse response, Logger log) throws IOException {
        // 忽略非POST请求
        if (request.getMethod().equalsIgnoreCase(WebContentGenerator.METHOD_POST)) {
            // 验证CSRF签名
            String CsrfToken = CsrfTokenManager.getTokenFromRequest(request);
            if(StringUtils.isBlank(CsrfToken)) {//用于json格式请求，从请求头中获取CsrfToken
                CsrfToken = request.getHeader("CsrfToken");
            }
            if (StringUtils.isBlank(CsrfToken) || !CsrfToken.equals(request.getSession().getAttribute(CsrfTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME))) {
                log.info("疑似csrf攻击！");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad or missing CSRF value");
                return false;
            }
        }
        return true;
    }

    public static boolean checkSessionKey(String signature, String rawData, String sessionKey){
        try {
            if(log.isDebugEnabled()){
                log.debug("before decoder rawData:[{}] ", rawData);
            }
            rawData = URLDecoder.decode(rawData, "UTF-8");
        }catch (UnsupportedEncodingException e){
            log.error(" checkSessionKey decode occur error. ", e);
            e.printStackTrace();
        }
        if(log.isDebugEnabled()){
            log.debug(" signature:[{}] ", signature);
            log.debug("decoder rawData:[{}] ", rawData);
        }
        try{
            String sign = SHA1.getSha1(rawData + sessionKey);
            if(sign.equals(signature)){
                return true;
            }
        }catch (Exception e){
            log.error(" checkSessionKey occur error:[{}] ", e);
            e.printStackTrace();
        }
        return false;
    }
}
