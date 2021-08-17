package com.yangy.web.service;

import com.yangy.web.common.PageBean;
import com.yangy.web.entity.View;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 景点表 服务类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
public interface ViewService extends IService<View> {

	public boolean editView(View view);
	
	public List<View> getList(PageBean pageBean);
	
	public View selectViewRecord(int viewId);
	
	public int deleteRecord(int viewId);
	
	public int getTotal();
}
