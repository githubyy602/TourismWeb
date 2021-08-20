package com.yangy.web.controller;


import com.yangy.web.constant.CommonConstants;
import com.yangy.web.constant.RedisConstants;
import com.yangy.web.entity.Order;
import com.yangy.web.mapper.OrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@PostMapping(value = "/crate.do")
	public String create(Order order, Model model){
		
		Integer viewId = order.getViewId();
		Optional<Order> orderOptional = Optional.of(order);
		if(!orderOptional.filter(o->StringUtils.isNotEmpty(o.getTouristName()) 
				&& StringUtils.isNotEmpty(o.getTouristPhone()) && Objects.nonNull(o.getTravelTime()))
				.isPresent()){
			return "redirect:/view/web/detail.do?viewId="+viewId+"&errorMsg=-1";
		}
		
		if(StringUtils.isEmpty(order.getPrice()) || order.getPrice().equals("免费")){
			order.setPeopleNum(null);
		}else {
			order.setPrice(order.getPrice().replace("元",""));
		}
		
		Integer userId = order.getUserId();
		String orderNo = this.generateOrderNo(userId);
		order.setOrderNo(orderNo);
		order.setStatus(CommonConstants.ORDER_STATUS_CREATED);
		
		int result = orderMapper.insert(order);
		if(result > 0){
			return "f_pay";	
		}else{
			model.addAttribute("errorMsg","创建订单失败！");
			return "redirect:/view/web/detail.do?viewId="+viewId;
		}
	}
	
	private String generateOrderNo(Integer userId){
		
		//设置订单号 格式:SM（深美） + 当前日期 + userId + 升序序号
		String today = DateUtils.format(new Date(),"yyyyMMdd", Locale.CHINA);
		String seqCache = RedisConstants.USER_ORDER_SEQUENCE_NO_CACHE + userId + today;
		Object userSeq = redisTemplate.opsForValue().get(seqCache);
		String seqNo = null;
		if(Objects.isNull(userSeq)){
			long num = redisTemplate.opsForValue().increment(seqCache);
			seqNo = String.format("%04d",num);
		}else{
			seqNo = String.format("%04d",Integer.valueOf(userSeq.toString()));
		}
		
		String orderNo = StringUtils.join("SM",today,String.format("%04d",userId),seqNo);
		return orderNo;
	}

}

