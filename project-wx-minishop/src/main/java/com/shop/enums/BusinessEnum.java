package com.shop.enums;

import com.shop.util.WxGlobalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务code
 *
 * @author zhouhw@fadada.com
 * @date 2019/3/26
 * @version 4.0.0
 */
public enum BusinessEnum {

	SUCCESS(WxGlobalConstants.SUCCESS, "请求成功"),

	ERROR(WxGlobalConstants.ERROR, "暂时无法加载，请稍后重试"),

	/**
	 *  会话过期，请重新打开页面
	 */
	SESSION_OUT_PLEASE_RE_OPEN_PAGE(WxGlobalConstants.ERROR + "0001", "会话过期，请重新打开页面"),
	/**
	 *  sessionKey校验失败
	 */
	SESSION_KEY_VALID_FAIL(WxGlobalConstants.ERROR + "0002", "Bad or missing signature value"),

	/**
	 * 暂时无法加载，请稍后重试
	 */
	CAN_NOT_LOAD_PLEASE_AGAIN(WxGlobalConstants.ERROR + "0003" , "暂时无法加载，请稍后重试"),
	/**
	 * 参数校验异常
	 */
	ARGUMENT_VALID_ERROR(WxGlobalConstants.ERROR + "0004" , "参数校验异常"),
	/**
	 *  登录失败
	 */
	LOGIN_FAIL(WxGlobalConstants.ERROR + "0005", "登录失败"),

	/**
	 *  获取当前操作用户数据失败
	 */
	GET_CURRENT_USER_INFO_ERROR(WxGlobalConstants.ERROR + "0006", "获取当前操作用户数据失败"),

	/**
	 *  无需重复收藏
	 */
	REPEAT_COLLECT(WxGlobalConstants.ERROR + "0007", "无需重复收藏"),

	/**
	 *  获取当前操作用户数据失败
	 */
	HAVE_NOT_COLLECT(WxGlobalConstants.ERROR + "0008", "无收藏数据"),

	;



	/** 字段值 */
	private String value;
	/** 字段值的实际意义 */
	private String valueInFact;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueInFact() {
		return valueInFact;
	}

	public void setValueInFact(String valueInFact) {
		this.valueInFact = valueInFact;
	}

	/**
	 * <b>概要：</b> 根据属性值匹配属性 <b>作者：</b>SUXH </br>
	 * <b>日期：</b>2015-3-17 </br>
	 * 
	 * @param value
	 *            需要匹配的属性值
	 * @return
	 */
	public static BusinessEnum convertByValue(String value) {
		for (BusinessEnum accountIsEmpower : BusinessEnum.values()) {
			if (accountIsEmpower.getValue().equals(value)) {
				return accountIsEmpower;
			}
		}
		return null;
	}

	/**
	 * <b>概要：</b> 获得枚举的所有值（all value） <b>作者：</b>SUXH </br>
	 * <b>日期：</b>2015-3-31 </br>
	 * 
	 * @return 枚举的所有值
	 */
	public static List<String> getValueList() {
		List<String> values = new ArrayList<String>();
		BusinessEnum[] accountIsEmpowerArr = BusinessEnum.values();
		for (BusinessEnum i : accountIsEmpowerArr) {
			values.add(i.getValue().toString());
		}
		return values;
	}

	BusinessEnum(String value, String valueInFact) {
		this.value = value;
		this.valueInFact = valueInFact;
	}
}
