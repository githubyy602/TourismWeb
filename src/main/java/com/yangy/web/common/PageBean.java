package com.yangy.web.common;

import lombok.Data;

/**
 * @Author: Yangy
 * @Date: 2021/8/13 16:37
 * @Description
 */
@Data
public class PageBean {
	
	private int pageSize = 6;
	
	private int pageIndex = 0;
	
	private int pageNum;
	
	private int total;
	
	private String queryName;
}
