package com.shop.bean.req;

import com.shop.bean.BaseReq;
import lombok.Data;

@Data
public class ProductReq extends BaseReq {

    private String keyWord;//搜索关键字

    //商品Id
    private String productId;
}
