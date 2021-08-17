package com.yangy.web.controller;


import com.yangy.web.service.PictureService;
import com.yangy.web.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片表 前端控制器
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@PostMapping(value = "/uploadPicture.do")
	public Integer uploadPicture(@RequestParam(value = "viewImg", required = false)MultipartFile file){
		return pictureService.uploadPicture(file);
	}
	
}

