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

/**
 * <p>
 * 路线表
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_line")
@ApiModel(value="Line对象", description="路线表")
public class Line implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "路线标题")
    private String lineTitle;

    @ApiModelProperty(value = "景点介绍")
    private String lineIntroduce;

    @ApiModelProperty(value = "路线具体规划")
    private String linePlane;

    @ApiModelProperty(value = "路线总长")
    private String lineTotal;

    @ApiModelProperty(value = "路线预估耗时")
    private String lineTime;

    @ApiModelProperty(value = "路线出行价格")
    private String price;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
