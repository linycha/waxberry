package com.tencent.wxcloudrun.modules.dto;

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
public class CartDTO {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private String userId;
    private Integer shopId;

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

    private String productName;
    private String shopName;
    private String sellPrice;
    private String logoImg;
}