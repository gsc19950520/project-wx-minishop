package com.shop.bean;

import lombok.Data;

@Data
public class BaseReq {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
}
