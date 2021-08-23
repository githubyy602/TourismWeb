package com.yangy.web.mapper;

import com.yangy.web.bean.OrderViewInfo;
import com.yangy.web.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
public interface OrderMapper extends BaseMapper<Order> {
	
	@Select("select tv.*,t.order_no,t.travel_time ,t.status orderStatus ,t.user_id ,tp.url as pictureUrl  from tb_order t left join tb_view tv on t.view_id = tv.id left join tb_picture tp on tv.picture_id = tp.id where t.user_id =#{userId}")
	List<OrderViewInfo> selectOrderViewList(@Param("userId")Integer userId);
	
}
