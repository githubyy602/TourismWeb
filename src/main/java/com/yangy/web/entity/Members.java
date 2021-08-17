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
 * 会员表
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_members")
@ApiModel(value="Members对象", description="会员表")
public class Members implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员类型名称")
    private String membersName;

    @ApiModelProperty(value = "会员购买价格")
    private String price;

    @ApiModelProperty(value = "会员折扣")
    private String discount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
