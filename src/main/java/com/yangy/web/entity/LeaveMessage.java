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
 * 留言表
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_leave_message")
@ApiModel(value="LeaveMessage对象", description="留言表")
public class LeaveMessage implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "景点id")
    private Integer viewId;

    @ApiModelProperty(value = "留言内容")
    private String message;

    @ApiModelProperty(value = "状态：1=正常，2=删除")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
