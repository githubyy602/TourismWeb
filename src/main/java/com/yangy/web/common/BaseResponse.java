package com.yangy.web.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Yangy
 * @Date: 2021/7/29 12:10
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
	
	private int code = 1000;
	
	private String message = "成功";
	
	private Object data;


	public BaseResponse(ResponseConstant responseConstant) {
		this.code = responseConstant.getCode();
		this.message = responseConstant.getMessage();
		this.data = null;
	}
}
