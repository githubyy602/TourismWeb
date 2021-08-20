package com.yangy.web.mapper;

import com.yangy.web.bean.LeaveMessageInfo;
import com.yangy.web.entity.LeaveMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 留言表 Mapper 接口
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
public interface LeaveMessageMapper extends BaseMapper<LeaveMessage> {
	
	@Select("select tlm.message ,tu.user_name from tb_leave_message tlm left join tb_user tu on tlm.user_id = tu.id where tlm.view_id = #{viewId}")
	List<LeaveMessageInfo> getMessageListByViewId(@Param("viewId") Integer viewId);
	
}
