package com.yangy.web.service.impl;

import com.yangy.web.entity.Picture;
import com.yangy.web.mapper.PictureMapper;
import com.yangy.web.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {

}
