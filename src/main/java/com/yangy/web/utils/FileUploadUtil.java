package com.yangy.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: Yangy
 * @Date: 2021/8/12 12:02
 * @Description 文件上传工具类
 */
@Slf4j
public class FileUploadUtil {

	public static boolean doUpload(MultipartFile file){
		if (Objects.isNull(file) || file.isEmpty()) {
			return false;
		}

		String rootPath = System.getProperty("user.dir");

		String fileName = file.getOriginalFilename();

		String filePath = rootPath + "\\src\\main\\resources\\static\\upload\\img\\";

		File dest = new File(filePath + fileName);

		try {
			file.transferTo(dest);

			log.info("上传成功");

			return true;

		} catch (IOException e) {
			log.error("FileUploadUtil.doUpload occurs an exception!\n{}", e);
		}

		return false;

	}


}
