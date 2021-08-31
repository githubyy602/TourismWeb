package com.yangy.web.exception;

/**
 * @Author: Yangy
 * @Date: 2021/8/27 11:59
 * @Description
 */
public class RepeatSubmitException extends Throwable {
	public RepeatSubmitException() {
		super("重复提交！");
	}
}
