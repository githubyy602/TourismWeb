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
 * 会员关联表
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user_members")
@ApiModel(value="UserMembers对象", description="会员关联表")
public class UserMembers implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "会员id")
    private String membersId;

    @ApiModelProperty(value = "会员开始时间")
    private String beginTime;

    @ApiModelProperty(value = "会员结束时间")
    private String endTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
