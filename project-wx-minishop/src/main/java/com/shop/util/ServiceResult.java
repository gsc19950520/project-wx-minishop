package com.shop.util;


import com.alibaba.fastjson.JSON;
import com.shop.enums.BusinessEnum;
import com.shop.util.common.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.LinkedHashMap;

@Slf4j
@Builder
@Data
public class ServiceResult implements Serializable {
	private static final long serialVersionUID = -4600212442781650484L;
	private String message;
	private String code;
	private Boolean success;
	private LinkedHashMap<String, Object> data;

	public ServiceResult() {
	}

	public void addData(String key, Object value) {
		this.data.put(key, value);
	}

	public Object getData(String key) {
		return this.data.get(key);
	}

	public String toJSON() {
		return JSON.toJSONString(this);
	}

	private static String $default$message() {
		return BusinessEnum.SUCCESS.getValueInFact();
	}

	private static String $default$code() {
		return BusinessEnum.SUCCESS.getValue();
	}

	private static Boolean $default$success() {
		return true;
	}

	private static LinkedHashMap<String, Object> $default$data() {
		return new LinkedHashMap();
	}

	ServiceResult(final String message, final String code, final Boolean success, final LinkedHashMap<String, Object> data) {
		this.message = message;
		this.code = code;
		this.success = success;
		this.data = data;
	}

	public static ServiceResult.ServiceResultBuilder builder() {
		return new ServiceResult.ServiceResultBuilder();
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public Boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(final Boolean success) {
		this.success = success;
	}

	public LinkedHashMap<String, Object> getData() {
		return this.data;
	}

	public void setData(final LinkedHashMap<String, Object> data) {
		this.data = data;
	}

	public static class ServiceResultBuilder {
		private boolean message$set;
		private String message$value;
		private boolean code$set;
		private String code$value;
		private boolean success$set;
		private Boolean success$value;
		private boolean data$set;
		private LinkedHashMap<String, Object> data$value;

		ServiceResultBuilder() {
		}

		public ServiceResult.ServiceResultBuilder message(final String message) {
			this.message$value = message;
			this.message$set = true;
			return this;
		}

		public ServiceResult.ServiceResultBuilder code(final String code) {
			this.code$value = code;
			this.code$set = true;
			return this;
		}

		public ServiceResult.ServiceResultBuilder success(final Boolean success) {
			this.success$value = success;
			this.success$set = true;
			return this;
		}

		public ServiceResult.ServiceResultBuilder data(final LinkedHashMap<String, Object> data) {
			this.data$value = data;
			this.data$set = true;
			return this;
		}

		public ServiceResult build() {
			String message$value = this.message$value;
			if (!this.message$set) {
				message$value = ServiceResult.$default$message();
			}

			String code$value = this.code$value;
			if (!this.code$set) {
				code$value = ServiceResult.$default$code();
			}

			Boolean success$value = this.success$value;
			if (!this.success$set) {
				success$value = ServiceResult.$default$success();
			}

			LinkedHashMap<String, Object> data$value = this.data$value;
			if (!this.data$set) {
				data$value = ServiceResult.$default$data();
			}

			return new ServiceResult(message$value, code$value, success$value, data$value);
		}

		public String toString() {
			return "ServiceResult.ServiceResultBuilder(message$value=" + this.message$value + ", code$value=" + this.code$value + ", success$value=" + this.success$value + ", data$value=" + this.data$value + ")";
		}
	}

}
