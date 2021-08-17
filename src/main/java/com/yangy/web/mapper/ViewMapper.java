package com.yangy.web.mapper;

import com.yangy.web.common.PageBean;
import com.yangy.web.entity.View;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 景点表 Mapper 接口
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
public interface ViewMapper extends BaseMapper<View> {
	
	List<View> selectViewList(PageBean pageBean);
	
	View selectViewRecord(int viewId);
}
