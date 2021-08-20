package com.yangy.web.controller;


import com.yangy.web.constant.CommonConstants;
import com.yangy.web.entity.LeaveMessage;
import com.yangy.web.entity.View;
import com.yangy.web.mapper.LeaveMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Controller
@RequestMapping("/leaveMessage")
public class LeaveMessageController {
	
	@Autowired
	private LeaveMessageMapper leaveMessageMapper;
	
	@PostMapping("/create.do")
	public String create(LeaveMessage leaveMessage, Model model){
		
		leaveMessage.setStatus(CommonConstants.LEAVE_MESSAGE_STATUS_NORMAL);
		int num = leaveMessageMapper.insert(leaveMessage);
		int result = 0;
		if(num > 0){
			model.addAttribute("msg","评论成功");
		}else{
			result = -2;
			model.addAttribute("msg","评论失败");
		}

		return "redirect:/view/web/detail.do?viewId="+leaveMessage.getViewId()+"&errorMsg="+result;
	}
	
}

