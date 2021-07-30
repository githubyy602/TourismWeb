package com.yangy.web.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Md5Util {
	
	 /**
     * MD5方法
     * 
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text){
    //加密后的字符串
	    String encodeStr= null;
	    try {
		    encodeStr = DigestUtils.md5DigestAsHex(text.getBytes("UTF-8"));
	    } catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
	    }
	    return encodeStr;
    }

	public static String generateRandomString(int length) {
		// 产生随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		// 循环length次
		for (int i = 0; i < length; i++) {
			// 产生0-2之间的一个随机数，既与a-z，A-Z，0-9三种可能
			int number = random.nextInt(3);
			long result;
			switch (number) {
			// 如果number产生的是数字0；
			case 0:
				// 产生A-Z的ASCII码
				result = Math.round(Math.random() * 25 + 65);
				// 将ASCII码转换成字符
				sb.append(String.valueOf((char) result));
				break;
			case 1:
				// 产生a-z的ASCII码
				result = Math.round(Math.random() * 25 + 97);
				sb.append(String.valueOf((char) result));
				break;
			case 2:
				// 产生0-9的数字
				sb.append(String.valueOf(new Random().nextInt(10)));
				break;
			}
		}
		return sb.toString();
	}
	
}
