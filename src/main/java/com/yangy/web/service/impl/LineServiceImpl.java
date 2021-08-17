package com.yangy.web.service.impl;

import com.yangy.web.entity.Line;
import com.yangy.web.mapper.LineMapper;
import com.yangy.web.service.LineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 路线表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements LineService {

}
