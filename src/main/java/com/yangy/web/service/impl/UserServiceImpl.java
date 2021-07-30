package com.yangy.web.service.impl;

import com.yangy.web.constant.CommonConstants;
import com.yangy.web.entity.User;
import com.yangy.web.mapper.UserMapper;
import com.yangy.web.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangy.web.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int createNewUser(User user) {
		//密码MD5加密处理
		String encryptPassword = Md5Util.md5(StringUtils.join(user.getPassword(),CommonConstants.PASSWORD_SALT));
		user.setPassword(encryptPassword);
		return userMapper.insert(user);
	}
}
