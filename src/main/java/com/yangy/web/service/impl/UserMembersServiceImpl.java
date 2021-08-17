package com.yangy.web.service.impl;

import com.yangy.web.entity.UserMembers;
import com.yangy.web.mapper.UserMembersMapper;
import com.yangy.web.service.UserMembersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员关联表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Service
public class UserMembersServiceImpl extends ServiceImpl<UserMembersMapper, UserMembers> implements UserMembersService {

}
