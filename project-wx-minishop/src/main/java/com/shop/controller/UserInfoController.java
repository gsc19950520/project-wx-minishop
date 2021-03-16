package com.shop.controller;

import com.shop.bean.req.PreIndexReq;
import com.shop.service.WxUserInfoService;
import com.shop.util.ServiceResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController extends BaseController {

    @Resource
    private WxUserInfoService userInfoService;


    /**
     * 用户免登校验
     * @param pir
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/checkOpenId")
    public ServiceResult checkOpenId(@RequestBody @Validated PreIndexReq pir) {
        HttpServletRequest request = this.getCurrentRequest();
        return this.userInfoService.checkOpenId(request, pir);
    }

}
