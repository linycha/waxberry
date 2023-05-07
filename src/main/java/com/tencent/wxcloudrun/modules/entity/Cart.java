package com.tencent.wxcloudrun.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
    * 购物车表
    */
@ApiModel(value="购物车表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cart")
public class Cart {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private String userId;

    @TableField(value = "product_id")
    @ApiModelProperty(value="")
    private Integer productId;

    @TableField(value = "num")
    @ApiModelProperty(value="")
    private Integer num;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_NUM = "num";

    public static final String COL_CREATE_TIME = "create_time";
}