package com.yangy.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Controller
@RequestMapping("/members")
public class MembersController {
	
	@GetMapping(value = "/toMemberList.do")
	public String toMemberList(Model model){
		return "memberList";
	}
	
}

