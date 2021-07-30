package com.yangy.web.mapper;

import com.yangy.web.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
