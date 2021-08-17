package com.yangy.web.service.impl;

import com.yangy.web.constant.CommonConstants;
import com.yangy.web.entity.Picture;
import com.yangy.web.mapper.PictureMapper;
import com.yangy.web.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangy.web.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {
	
	@Autowired
	private PictureMapper pictureMapper;
	
	@Override
	public Integer uploadPicture(MultipartFile file) {
		
		if(FileUploadUtil.doUpload(file)){
			String fileName = file.getOriginalFilename();
			Picture newPic = new Picture(fileName,fileName,CommonConstants.PICTURE_TYPE_OF_VIEW);
			pictureMapper.insert(newPic);
			Integer pictureId = newPic.getId();
			return pictureId;
		}
		return null;
	}
}
 