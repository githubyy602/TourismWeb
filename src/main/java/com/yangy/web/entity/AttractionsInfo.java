package com.yangy.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 旅游景点信息表
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbAttractionsInfo对象", description="旅游景点信息表")
@TableName("tb_attractions_info")
public class AttractionsInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "景点名称")
    private String name;

    @ApiModelProperty(value = "景点描述")
    private String desc;

    @ApiModelProperty(value = "图片1")
    private Integer pic1;

    @ApiModelProperty(value = "图片2")
    private Integer pic2;

    @ApiModelProperty(value = "图片3")
    private Integer pic3;

    @ApiModelProperty(value = "图片4")
    private Integer pic4;

    @ApiModelProperty(value = "推荐路线")
    private String recommendLine;

    @ApiModelProperty(value = "当前价格")
    private String presentPrice;

    @ApiModelProperty(value = "原价")
    private String prePrice;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
