package com.yangy.web.service.impl;

import com.yangy.web.entity.LeaveMessage;
import com.yangy.web.mapper.LeaveMessageMapper;
import com.yangy.web.service.LeaveMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Service
public class LeaveMessageServiceImpl extends ServiceImpl<LeaveMessageMapper, LeaveMessage> implements LeaveMessageService {

}
