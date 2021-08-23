package com.yangy.web.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangy.web.bean.LeaveMessageInfo;
import com.yangy.web.common.PageBean;
import com.yangy.web.entity.Line;
import com.yangy.web.entity.Order;
import com.yangy.web.entity.User;
import com.yangy.web.entity.View;
import com.yangy.web.mapper.LeaveMessageMapper;
import com.yangy.web.mapper.LineMapper;
import com.yangy.web.mapper.OrderMapper;
import com.yangy.web.service.ViewService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 景点表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private LineMapper lineMapper;
	
	@Autowired
	private LeaveMessageMapper leaveMessageMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@GetMapping("/list.do")
	public String toListPage(PageBean pageBean, Model model){
		if(Objects.isNull(pageBean)) pageBean = new PageBean();
		List<View> viewList = viewService.getList(pageBean);
		model.addAttribute("viewList",viewList);
		return "viewList";
	}
	
	@PostMapping("/list.do")
	public String getList(PageBean pageBean, Model model){
		if(Objects.isNull(pageBean)) pageBean = new PageBean();
		pageBean.setPageIndex((pageBean.getPageIndex()-1)*pageBean.getPageSize());
		List<View> viewList = viewService.getList(pageBean);
		model.addAttribute("viewList",viewList);
		return "viewList::refreshList";
	}
	
	@ResponseBody
	@PostMapping("/count.do")
	public Integer toListPage(){
		return viewService.getTotal();
	}
	
	@GetMapping(value = "/edit.do")
	public String toViewEditPage(@RequestParam(value = "viewId",required = false) Integer viewId,Model model){
		if(Objects.nonNull(viewId) &&  viewId.intValue() > 0){
			View view = viewService.selectViewRecord(viewId);
			if(Objects.nonNull(view)){
				model.addAttribute("view",view);
			}else{
				model.addAttribute("view",new View());
			}
			
		}else{
			model.addAttribute("view",new View());
		}
		
		List<Line> lineList = lineMapper.selectList(null);
		model.addAttribute("lineList",lineList);
		return "viewEdit";
	}
	
	@PostMapping(value = "/createView.do",consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String createView(View view,Model model){
		if(viewService.editView(view)){
			model.addAttribute("msg","操作成功！");
			return "redirect:/view/list.do";	
		}else{
			model.addAttribute("msg","操作失败！");
			return "redirect:/view/edit.do";	
		}
	}
	
	@ResponseBody
	@PostMapping(value = "/deleteView.do")
	public Integer deleteView(Integer viewId){
		if(Objects.isNull(viewId) || viewId.intValue() == 0) return null;
		return viewService.deleteRecord(viewId);
	}
	
	/**
	* @Author Yangy
	* @Description 旅游网站web端index页面
	* @Date 14:35 2021/8/18
	* @Param [pageBean, model]
	* @return java.lang.String
	**/
	@GetMapping("/web/index.do")
	public String toIndexPage(Model model){
		List<View> viewList = viewService.getList(new PageBean());
		model.addAttribute("viewList",viewList);
		return "f_index";
	}
	
	/**
	* @Author Yangy
	* @Description 景点详情页
	* @Date 15:36 2021/8/19
	* @Param [model]
	* @return java.lang.String
	**/
	@GetMapping("/web/detail.do")
	public String toDetail(Integer viewId, 
	                       @RequestParam(value = "errorMsg",required = false) String errorMsg,
	                       Model model){
		View view = viewService.selectViewRecord(viewId);
		
		Subject subject = SecurityUtils.getSubject();
		User loginUser = (User) subject.getSession().getAttribute("tourist");
		
		List<LeaveMessageInfo> messageList = leaveMessageMapper.getMessageListByViewId(viewId);
		
		Integer userId = Objects.nonNull(loginUser) ? loginUser.getId() : null;
		
		model.addAttribute("view",view);
		model.addAttribute("userId",userId);
		model.addAttribute("messageList",messageList);
		if(StringUtils.equals("-1",errorMsg)){
			model.addAttribute("errorMsg","订单创建失败！");	
		}else if(StringUtils.equals("-2",errorMsg)){
			model.addAttribute("errorMsg","评论失败");	
		}
		
		if(Objects.nonNull(userId)){
			//待支付订单
			QueryWrapper wrapper = new QueryWrapper();
			wrapper.eq("user_id",userId);
			wrapper.le("status",2);
			List<Order> orderList = orderMapper.selectList(wrapper);
			model.addAttribute("orderList",orderList);
		}
		
		return "f_viewDetail";
	}
}

