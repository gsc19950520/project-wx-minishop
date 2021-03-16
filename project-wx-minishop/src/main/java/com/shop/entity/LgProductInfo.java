package com.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("lg_product_info")
public class LgProductInfo extends Model<LgProductInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private String productId;

    /**
     * 商品编号
     */
    @TableField("product_number")
    private String productNumber;

    /**
     * 商品分类ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 商品点击数
     */
    @TableField("click_num")
    private Integer clickNum;

    /**
     * 收藏基数
     */
    @TableField("base_collect_num")
    private Integer baseCollectNum;

    /**
     * 商品库存数量
     */
    @TableField("inventory_num")
    private Integer inventoryNum;

    /**
     * 市场价
     */
    @TableField("market_price")
    private Double marketPrice;

    /**
     * 售价
     */
    @TableField("sale_price")
    private Double salePrice;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 描述
     */
    private String description;

    /**
     * 缩略图
     */
    @TableField("small_pic")
    private String smallPic;

    /**
     * 原图
     */
    @TableField("original_pic")
    private String originalPic;

    /**
     * 大图
     */
    @TableField("big_pic")
    private String bigPic;

    /**
     * 是否上架（0否1是）
     */
    @TableField("is_putaway")
    private Integer isPutaway;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
