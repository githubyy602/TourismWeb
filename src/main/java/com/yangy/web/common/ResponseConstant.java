package com.yangy.web.common;

import lombok.Data;

/**
 * @Author: Yangy
 * @Date: 2021/7/29 12:16
 * @Description
 */
public enum  ResponseConstant {
	PARAM_ERROR(2000,"参数错误"),
	OPERATE_FAIL(2001,"操作失败");

	ResponseConstant(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;
	
	private String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
