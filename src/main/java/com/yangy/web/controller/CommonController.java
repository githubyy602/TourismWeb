package com.yangy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Yangy
 * @Date: 2021/7/29 16:05
 * @Description 公用控制器
 */
@Controller
public class CommonController {
	
	//防止找不到favicon.ico报错
    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    	
    }
	
	@GetMapping(value = {"/","/index"})
	public String getIndex(@PathVariable("url") String url){
		return "f_index";
	}
	
	@GetMapping(value = {"/{url}"})
	public String toPage(@PathVariable("url") String url){
		return url;
	}
	
}
