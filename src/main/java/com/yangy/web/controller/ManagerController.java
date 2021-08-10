package com.yangy.web.controller;

import com.yangy.web.entity.User;
import com.yangy.web.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author yangy
 * @description 管理员控制器
 */

@Controller
@Slf4j
@RequestMapping("/admin")
public class ManagerController {

    @Autowired
    private MailUtil mailUtil;

    @GetMapping(value = {"","/","/login"})
    public String redirect(){
        return "login";
    }

    @PostMapping("/login.do")
    public String login(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //把传进来的用户名和密码封装成token，通过subject交给shiro去做
        try {
            //没有异常，来到首页
            subject.login(token);
            User user = (User) subject.getPrincipal();
            subject.getSession().setAttribute("admin",user);
            return "f_index";
        } catch (UnknownAccountException e) {
            //用户名错误，借助model将错误信息带给前端
            log.error("用户名错误！\n{}",e);
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //密码不合法，借助model将错误信息带给前端
            log.error("密码错误！\n{}",e);
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    //未授权的页面
    @GetMapping("/unauth")
    @ResponseBody
    public String unauth(){
        return "未授权，无法访问！！！";
    }

    //退出登陆
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login2";
    }

    @GetMapping("/sendMail")
    public String sendMail(){
        String[] to = {"2829025551@qq.com"};
        mailUtil.sendHtmlMail("你好","haha",to);
        return "successful";
    }
}
