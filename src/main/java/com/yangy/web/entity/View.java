package com.yangy.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 景点表
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_view")
@ApiModel(value="View对象", description="景点表")
public class View implements Serializable {

    private static final long serialVersionUID=1L;

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
    
    @TableField(exist = false)
    private String pictureUrl;
    
    @TableField(exist = false)
    private String lineDesc;
}
