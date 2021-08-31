package com.yangy.web.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangy.web.annotation.CheckRepeatSubmit;
import com.yangy.web.bean.OrderListBean;
import com.yangy.web.bean.OrderViewInfo;
import com.yangy.web.constant.CommonConstants;
import com.yangy.web.constant.RedisConstants;
import com.yangy.web.entity.Order;
import com.yangy.web.mapper.OrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@CheckRepeatSubmit(value = "orderCreate")
	@PostMapping(value = "/create.do")
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
			return "redirect:/payRecord/toPay.do?orderNo="+orderNo+"&userId="+userId;	
		}else{
			model.addAttribute("errorMsg","创建订单失败！");
			return "redirect:/view/web/detail.do?viewId="+viewId;
		}
	}
	
	private String generateOrderNo(Integer userId){
		
		//设置订单号 格式:SM（深美） + 当前日期 + userId + 升序序号
		String today = DateUtils.format(new Date(),"yyyyMMdd", Locale.CHINA);
		String seqCache = RedisConstants.USER_ORDER_SEQUENCE_NO_CACHE + userId + today;
		long num = redisTemplate.opsForValue().increment(seqCache);
		String seqNo = String.format("%04d",num);
		String orderNo = StringUtils.join("SM",today,String.format("%04d",userId),seqNo);
		return orderNo;
	}
	
	/**
	* @Author Yangy
	* @Description 进入订单列表
	* @Date 11:30 2021/8/23
	* @Param []
	* @return java.lang.String
	**/
	@GetMapping(value = "/myOrderList.do")
	public String myOrderList(Integer userId,Model model){
		if(Objects.nonNull(userId)){
			List<OrderViewInfo> orderList = orderMapper.selectOrderViewList(userId);
			model.addAttribute("orderList",orderList);
		}
		return "f_payList";
	}
	
	/**
	* @Author Yangy
	* @Description 订单管理功能-列表
	* @Date 11:29 2021/8/24
	* @Param [userId, model]
	* @return java.lang.String
	**/
	@GetMapping(value = "/manageList.do")
	public String manageList(OrderListBean orderListBean, Model model){
		orderListBean.setPageIndex((orderListBean.getPageIndex() > 0 ? orderListBean.getPageIndex()-1 : 0)*orderListBean.getPageSize());
		List<OrderViewInfo> orderList = orderMapper.selectAllOrderList(orderListBean);
		if(Objects.nonNull(orderListBean) && StringUtils.isNotEmpty(orderListBean.getQueryName())){
			orderList = orderList.stream().filter(o->Objects.nonNull(o) && o.getViewTitle().contains(orderListBean.getQueryName()))
					.collect(Collectors.toList());
		}
		model.addAttribute("orderList",orderList);
		return "orderList";
	}
	
	@ResponseBody
	@PostMapping("count.do")
	public Integer count(){
		return orderMapper.selectCount(null);
	}
	
}

