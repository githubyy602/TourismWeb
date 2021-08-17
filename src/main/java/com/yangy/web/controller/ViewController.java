package com.yangy.web.controller;


import com.yangy.web.common.PageBean;
import com.yangy.web.entity.View;
import com.yangy.web.service.ViewService;
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
	
	
	
}

