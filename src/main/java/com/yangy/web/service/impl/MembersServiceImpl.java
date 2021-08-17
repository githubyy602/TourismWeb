package com.yangy.web.service.impl;

import com.yangy.web.entity.Members;
import com.yangy.web.mapper.MembersMapper;
import com.yangy.web.service.MembersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Service
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements MembersService {

}
