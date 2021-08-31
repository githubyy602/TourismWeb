package com.yangy.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Yangy
 * @Date: 2021/8/27 12:17
 * @Description
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(RepeatSubmitException.class)
	public String submitError() {
		return "请勿重复提交！<a href=\"javascript:window.history.go(-1);\">【返回上一页】</a>";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String error() {
//		return "error";
//	}
}
