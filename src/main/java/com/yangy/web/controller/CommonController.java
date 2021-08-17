package com.yangy.web.controller;

import com.yangy.web.constant.CommonConstants;
import com.yangy.web.entity.User;
import com.yangy.web.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Yangy
 * @Date: 2021/7/29 16:05
 * @Description 公用控制器
 */
@Controller
public class CommonController {
	
	//防止找不到favicon.ico报错
//    @GetMapping("favicon.ico")
//    @ResponseBody
//    void returnNoFavicon() {
//    	
//    }
	
	@GetMapping(value = {"/","/index"})
	public String getIndex(){
		return "f_index";
	}
	
	
	/**
	* @Author Yangy
	* @Description 登录接口（游客客户/管理员同用）
	* @Date 14:44 2021/8/11
	* @Param [username, password, loginType=1为游客登录，=2为管理员登录, model]
	* @return java.lang.String
	**/
	@PostMapping("/login.do")
    public String login(String username, String password,
	                    int logintype,
	                    Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,Md5Util.md5(StringUtils.join(password,CommonConstants.PASSWORD_SALT)));
        //把传进来的用户名和密码封装成token，通过subject交给shiro去做
        try {
            //没有异常，来到首页
            subject.login(token);
            User user = (User) subject.getPrincipal();
            if(CommonConstants.USER_TYPE_OF_TOURIST == logintype){
	            subject.getSession().setAttribute("tourist",user);
	            return "f_index";
            }else if(CommonConstants.USER_TYPE_OF_MANAGE == logintype){
	            subject.getSession().setAttribute("manager",user);
	            return "index";
            }
            
        }catch(IncorrectCredentialsException ice){
            model.addAttribute("msg","用户名或密码不正确！");
        }catch(UnknownAccountException uae){
        	model.addAttribute("msg","未知账户！");
        }catch(LockedAccountException lae){
        	model.addAttribute("msg","账户已锁定！");
        }catch(ExcessiveAttemptsException eae){
        	model.addAttribute("msg","用户名或密码错误次数太多！");
        }catch(AuthenticationException ae){
            model.addAttribute("msg","验证未通过！");
        }catch (Exception e) {
        	model.addAttribute("msg","验证未通过！");
		}
		if(CommonConstants.USER_TYPE_OF_TOURIST == logintype){
        	return "f_login";
		}else if(CommonConstants.USER_TYPE_OF_MANAGE == logintype){
			return "login";
		}
		
		return "f_login";
    }
	
    @PostMapping("/logout")
	public String logout(){
		return "/";
    }
    
}
