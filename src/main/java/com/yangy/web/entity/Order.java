package com.yangy.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_order")
@ApiModel(value="Order对象", description="订单表")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "景点id")
    private Integer viewId;

    @ApiModelProperty(value = "订单成交价格")
    private String price;

    @ApiModelProperty(value = "游客姓名")
    private String touristName;

    @ApiModelProperty(value = "游客电话")
    private String touristPhone;

    @ApiModelProperty(value = "出行人数")
    private Integer peopleNum;

    @ApiModelProperty(value = "旅游出行时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date travelTime;

    @ApiModelProperty(value = "订单状态：1=已创建，2=未支付，3=已支付，4=已过期")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
