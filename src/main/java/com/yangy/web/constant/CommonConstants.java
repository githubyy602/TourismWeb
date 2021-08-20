package com.yangy.web.constant;

/**
 * @Author: Yangy
 * @Date: 2021/7/29 11:51
 * @Description
 */
public class CommonConstants {
	
	public static final String PASSWORD_SALT = "jdoifujuhug&&@@$$$";
	
	//用户类型：1=游客，2=管理员
	public final static int USER_TYPE_OF_TOURIST = 1;
	public final static int USER_TYPE_OF_MANAGE = 2;
	
	//图片类型：1=头像，2=景点
	public final static int PICTURE_TYPE_OF_HEAD = 1;
	public final static int PICTURE_TYPE_OF_VIEW = 2;
	
	//订单状态：1=已创建，2=未支付，3=已支付，4=已过期
	public final static int ORDER_STATUS_CREATED = 1;
	public final static int ORDER_STATUS_NO_PAY = 2;
	public final static int ORDER_STATUS_PAYED = 3;
	public final static int ORDER_STATUS_EXPIRE = 4;
	
	//状态：1=正常，2=删除
	public final static int LEAVE_MESSAGE_STATUS_NORMAL = 1;
	public final static int LEAVE_MESSAGE_STATUS_DELETED = 2;
}
