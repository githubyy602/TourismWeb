package com.yangy.web.service.impl;

import com.yangy.web.common.PageBean;
import com.yangy.web.entity.View;
import com.yangy.web.mapper.ViewMapper;
import com.yangy.web.service.ViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 景点表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Service
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements ViewService {
	
	@Autowired
	private ViewMapper viewMapper;
	
	@Override
	public boolean editView(View view) {
		if(Objects.isNull(view)) return false;
		
		Integer id = view.getId();
		if(Objects.isNull(id) || id.intValue() == 0){
			//新增
			view.setStatus(1);
			viewMapper.insert(view);	
			return true;
		}else{
			//编辑更新
			viewMapper.updateById(view);
			return true;
		}
	}

	@Override
	public List<View> getList(PageBean pageBean) {
		return viewMapper.selectViewList(pageBean);
	}

	@Override
	public View selectViewRecord(int viewId) {
		return viewMapper.selectViewRecord(viewId);
	}

	@Override
	public int deleteRecord(int viewId) {
		return viewMapper.deleteById(viewId);
	}

	@Override
	public int getTotal() {
		return viewMapper.selectCount(null);
	}
}   
