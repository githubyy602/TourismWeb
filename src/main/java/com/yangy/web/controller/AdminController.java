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
public class AdminController {

    @Autowired
    private MailUtil mailUtil;

    @GetMapping(value = {"","/","/login"})
    public String toLogin(){
        return "login";
    }
    
    @GetMapping(value = "/index.do")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/sendMail")
    public String sendMail(){
        String[] to = {"2829025551@qq.com"};
        mailUtil.sendHtmlMail("你好","haha",to);
        return "successful";
    }
    
    @GetMapping("/toPassword.do")
    public String toPassword(){
        return "pass";
    }
    
}
