package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 微信小程序登录信息表
 * </p>
 *
 * @author gaosc
 * @since 2020-03-31
 */
@Data
@TableName("t_wx_user_info")
public class WxUserInfo {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属企业的corpid（企业微信特有）
     */
    private String corpid;

    /**
     * 用户在企业内的UserID(企业微信特有标识)
     */
    private String userid;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 用户redis当中的key
     */
    @TableField("third_session_key")
    private String thirdSessionKey;

    /**
     * 用户在开放平台的唯一标识符(在法大大小程序里面的唯一标识)
     */
    private String unionid;

    /**
     * 用户姓名（实名认证之后才有）
     */
    private String name;

    /**
     * 手机设备号（企业微信特有）
     */
    private String deviceid;

    /**
     * 成员票据，最大为512字节。
     */
    @TableField("user_ticket")
    private String userTicket;

    /**
     * 3企业微信用户，4微信用户
     */
    private Integer source;

    /**
     * 是否绑定手机号(0否1是)
     */
    @TableField("is_bind_mobile")
    private Integer isBindMobile;

    /**
     * 用户登录手机号码
     */
    private String mobile;

    /**
     * 业务库标识Id
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 业务库唯一标识
     */
    private String account;

    /**
     * 登录次数
     */
    @TableField("login_number")
    private Integer loginNumber;

    /**
     * 最近一次登陆时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 账户状态:0为认证，1已提交待审核，2审核通过，3审核不通过
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 是否是管理员（0否1是）
     */
    @TableField("is_admin")
    private Integer isAdmin;

}
