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
 * 图片表
 * </p>
 *
 * @author Yangy
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_picture")
@ApiModel(value="Picture对象", description="图片表")
public class Picture implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "图片标题")
    private String pictureTitle;

    @ApiModelProperty(value = "图片路径")
    private String url;

    @ApiModelProperty(value = "图片类型：1=头像，2=景点")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Picture(String pictureTitle, String url, Integer type) {
        this.pictureTitle = pictureTitle;
        this.url = url;
        this.type = type;
    }
}
