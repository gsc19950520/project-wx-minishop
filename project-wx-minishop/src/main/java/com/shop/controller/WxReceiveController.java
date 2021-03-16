package com.shop.controller;

import com.shop.service.WxConfigService;
import com.shop.util.crypt.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController("wxReceiveController")
@RequestMapping
public class WxReceiveController {

    @Resource
    private WxConfigService wxConfigService;

    /**
     * 微信回调
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/receive", method = {RequestMethod.GET, RequestMethod.POST})
    public String receive(HttpServletRequest request) {
        log.info("WxReceiveController-receive");
        return this.wxConfigService.receive(request);
    }
}
