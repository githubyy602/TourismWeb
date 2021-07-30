package com.yangy.web.service;

import com.yangy.web.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
public interface UserService extends IService<User> {
	
	int createNewUser(User user);
	
}
