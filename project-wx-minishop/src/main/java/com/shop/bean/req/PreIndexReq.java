package com.shop.bean.req;

import com.alibaba.fastjson.JSON;
import com.shop.enums.BusinessEnum;
import com.shop.util.MyException;
import com.shop.util.common.JSONUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.net.URLDecoder;

@Data
public class PreIndexReq {

    @NotBlank(message = "code不能为空")
    private String code;

//    @NotBlank(message = "signature不能为空")
//    private String signature;
//
//    @NotBlank(message = "rawData不能为空")
//    private String rawData;

    /**
     * 加密的时间戳
     */
    private String encryptTimeStamp;

//    public String getRawData(){
//        try{
//            rawData = URLDecoder.decode(rawData, "UTF-8");
//        }catch (Exception e){
//            throw new MyException(BusinessEnum.CAN_NOT_LOAD_PLEASE_AGAIN.getValue(), BusinessEnum.CAN_NOT_LOAD_PLEASE_AGAIN.getValueInFact());
//        }
//        return rawData;
//    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
