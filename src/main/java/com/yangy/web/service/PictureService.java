package com.yangy.web.service;

import com.yangy.web.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
public interface PictureService extends IService<Picture> {
	
	public Integer uploadPicture(MultipartFile file);
	
}
