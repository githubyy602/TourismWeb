package com.yangy.web.controller;


import com.yangy.web.common.BaseResponse;
import com.yangy.web.common.ResponseConstant;
import com.yangy.web.entity.User;
import com.yangy.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/register.do")
	public BaseResponse register(@RequestBody @Validated User user,
	                             BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return new BaseResponse(ResponseConstant.PARAM_ERROR);
		}
		
		int num = userService.createNewUser(user);
		if(num > 0) return new BaseResponse();
		
		return new BaseResponse(ResponseConstant.OPERATE_FAIL);
	}
	
}

