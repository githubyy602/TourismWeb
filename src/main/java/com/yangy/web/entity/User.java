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
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Yangy
 * @since 2021-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbUser对象", description="用户表")
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
//    @NotBlank
//    @Length(min = 4,max = 20)
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "性别：0=男，1=女")
    private Integer sex;
    
//    @NotBlank
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "头像url")
    private String photo;
    
//    @NotBlank
//    @Length(min = 6,max = 20)
    @ApiModelProperty(value = "登录账号名")
    private String loginName;
    
//    @NotBlank
//    @Length(min = 6,max = 20)
    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "用户类型：1=旅客，2=管理员")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
