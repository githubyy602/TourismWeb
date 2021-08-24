package com.yangy.web.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Yangy
 * @Date: 2021/8/23 11:36
 * @Description
 */
@Data
public class OrderViewInfo {
	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "景点标题")
    private String viewTitle;

    @ApiModelProperty(value = "景点介绍")
    private String viewIntroduce;

    @ApiModelProperty(value = "景点图片id")
    private Integer pictureId;

    @ApiModelProperty(value = "景点联系电话")
    private String phone;

    @ApiModelProperty(value = "景点负责人")
    private String manager;

    @ApiModelProperty(value = "景点门票价格")
    private String price;

    @ApiModelProperty(value = "景点类型：1=收费，2=免费")
    private Integer type;

    @ApiModelProperty(value = "路线id")
    private Integer lineId;

    @ApiModelProperty(value = "状态：1=正常，2=禁用，3=删除")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    private String orderNo;
    
    private String pictureUrl;
    
    private Integer userId;
    
    private Date travelTime;
    //订单状态
    private Integer orderStatus;
    //订单创建时间
    private Date orderTime;
}
