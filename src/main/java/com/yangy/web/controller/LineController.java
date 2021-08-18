package com.yangy.web.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangy.web.common.PageBean;
import com.yangy.web.entity.Line;
import com.yangy.web.mapper.LineMapper;
import org.apache.commons.lang3.StringUtils;
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
 * 路线表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Controller
@RequestMapping("/line")
public class LineController {
	
	@Autowired
	private LineMapper lineMapper;
	
	
	@GetMapping("/list.do")
	public String toListPage(PageBean pageBean, Model model){
		if(Objects.isNull(pageBean)) pageBean = new PageBean();
		QueryWrapper query = new QueryWrapper();
		query.orderByDesc("create_time");
		if(StringUtils.isNotEmpty(pageBean.getQueryName())){
			query.like("line_title ",pageBean.getQueryName());
		}
		List<Line> lineList = lineMapper.selectPage(new Page<>(pageBean.getPageIndex(),pageBean.getPageSize())
				,query).getRecords();
		model.addAttribute("lineList",lineList);
		return "lineList";
	}
	
	@PostMapping("/list.do")
	public String getList(PageBean pageBean, Model model){
		if(Objects.isNull(pageBean)) pageBean = new PageBean();
		QueryWrapper query = new QueryWrapper();
		query.orderByDesc("create_time");
		if(StringUtils.isNotEmpty(pageBean.getQueryName())){
			query.like("line_title ",pageBean.getQueryName());
		}
		
		List<Line> lineList = lineMapper.selectPage(new Page<>(pageBean.getPageIndex(),pageBean.getPageSize())
				,query).getRecords();
		model.addAttribute("lineList",lineList);
		return "lineList::refreshList";
	}
	
	@ResponseBody
	@PostMapping("/count.do")
	public Integer toListPage(){
		return lineMapper.selectCount(null);
	}
	
	@GetMapping(value = "/edit.do")
	public String tolineEditPage(@RequestParam(value = "lineId",required = false) Integer lineId, Model model){
		if(Objects.nonNull(lineId) &&  lineId.intValue() > 0){
			QueryWrapper<Line> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",lineId);
			Line line = lineMapper.selectOne(queryWrapper);
			if(Objects.nonNull(line)){
				model.addAttribute("line",line);
			}else{
				model.addAttribute("line",new Line());
			}
			
		}else{
			model.addAttribute("line",new Line());
		}
		return "lineEdit";
	}
	
	@PostMapping(value = "/createLine.do",consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String createline(Line line,Model model){
		Integer id = line.getId();
		if(Objects.nonNull(id) && id.intValue() > 0){
			lineMapper.updateById(line);
			model.addAttribute("msg","操作成功！");
			return "redirect:/line/list.do";
		}else{
			lineMapper.insert(line);
			model.addAttribute("msg","操作成功！");
			return "redirect:/line/list.do";
		}
	}
	
	@ResponseBody
	@PostMapping(value = "/deleteLine.do")
	public Integer deleteline(Integer lineId){
		if(Objects.isNull(lineId) || lineId.intValue() == 0) return null;
		return lineMapper.deleteById(lineId);
	}
	
}

