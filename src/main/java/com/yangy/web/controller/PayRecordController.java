package com.yangy.web.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangy.web.constant.CommonConstants;
import com.yangy.web.entity.Order;
import com.yangy.web.entity.PayRecord;
import com.yangy.web.entity.User;
import com.yangy.web.entity.View;
import com.yangy.web.mapper.OrderMapper;
import com.yangy.web.mapper.UserMapper;
import com.yangy.web.mapper.ViewMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * <p>
 * 支付记录表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Controller
@RequestMapping("/payRecord")
public class PayRecordController {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ViewMapper viewMapper;
	
	@GetMapping("/toPay.do")
	public String toPayPage(PayRecord payRecord, Model model){
		
		if(Objects.nonNull(payRecord) && Objects.nonNull(payRecord.getOrderNo())
				&& Objects.nonNull(payRecord.getUserId())){
			Wrapper queryWrapper = new QueryWrapper<>().eq("order_no",payRecord.getOrderNo());
			Order needPayOrder = orderMapper.selectOne(queryWrapper);
			User payUser = userMapper.selectById(payRecord.getUserId());
			View orderView = viewMapper.selectViewRecord(needPayOrder.getViewId());
			model.addAttribute("payOrder",needPayOrder);
			model.addAttribute("payUser",payUser);
			model.addAttribute("orderView",orderView);
		}
		
		return "f_pay";
	}
	
	/**
	* @Author Yangy
	* @Description 扫码支付
	* @Date 15:18 2021/8/23
	* @Param [orderNo, model]
	* @return java.lang.String
	**/
	@GetMapping("/pay.do")
	public String pay(String orderNo,Model model){
		Wrapper queryWrapper = new QueryWrapper<>().eq("order_no",orderNo);
		Order payOrder = orderMapper.selectOne(queryWrapper);
		
		Order updateOrder = new Order();
		updateOrder.setId(payOrder.getId());
		updateOrder.setStatus(CommonConstants.ORDER_STATUS_PAYED);
		orderMapper.updateById(updateOrder);

		Subject subject = SecurityUtils.getSubject();
		User loginUser = (User) subject.getSession().getAttribute("tourist");
		
		model.addAttribute("orderNo",orderNo);
		model.addAttribute("userId",Objects.nonNull(loginUser) ? loginUser.getId() : null);
		return "f_payResult";
	}
}

