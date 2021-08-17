package com.yangy.web.service.impl;

import com.yangy.web.entity.PayRecord;
import com.yangy.web.mapper.PayRecordMapper;
import com.yangy.web.service.PayRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付记录表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Service
public class PayRecordServiceImpl extends ServiceImpl<PayRecordMapper, PayRecord> implements PayRecordService {

}
