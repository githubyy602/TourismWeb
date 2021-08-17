package com.yangy.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangy.web.common.BaseResponse;
import com.yangy.web.common.ResponseConstant;
import com.yangy.web.constant.CommonConstants;
import com.yangy.web.entity.User;
import com.yangy.web.mapper.UserMapper;
import com.yangy.web.service.UserService;
import com.yangy.web.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"/","/index"})
	public String getIndex(){
		return "f_index";
	}
	
	@GetMapping(value = "/register.do")
	public String toRegisterPage(){
		return "f_register";
	}
	
	@GetMapping(value = "/index.do")
	public String toIndexPage(){
		return "f_index";
	}
	
	@GetMapping(value = "/login.do")
	public String toLoginPage(){
		return "f_login";
	}
	
	/**
	* @Author Yangy
	* @Description 游客用户注册
	* @Date 20:17 2021/8/2
	* @Param [user, model]
	* @return java.lang.String
	**/
	@PostMapping(value = "/register.do",consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String register(User user, Model model){
		
		if(Objects.isNull(user.getSex())) user.setSex(0);
		int num = 0;
		
		try {
			User existUser = userMapper.selectOne(new QueryWrapper<User>().eq("login_name",user.getUserName()));
			if(Objects.nonNull(existUser)) {
				model.addAttribute("message",ResponseConstant.USER_NAME_ALREADY_EXIST.getMessage());
				return "f_register"; 
			}
			
			user.setType(CommonConstants.USER_TYPE_OF_TOURIST);
			num = userService.createNewUser(user);
		} catch (Exception e) {
			log.error("UserController.register occurs exception!\n{}",e);
			model.addAttribute("message",ResponseConstant.OPERATE_FAIL.getMessage());
			return "f_register"; 
		}

		if(num > 0){
//			model.addAttribute("userName",user.getUserName());
//			model.addAttribute("userType",CommonConstants.USER_TYPE_OF_TOURIST);
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("tourist",user);
			return "f_index"; 
		}
		
		model.addAttribute("message",ResponseConstant.OPERATE_FAIL.getMessage());
		return "f_register"; 
	}
    
}

