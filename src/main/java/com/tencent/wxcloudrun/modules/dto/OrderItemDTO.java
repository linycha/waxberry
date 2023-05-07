package com.tencent.wxcloudrun.modules.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
    * 订单商品表
    */
@ApiModel(value="订单商品表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private String orderNo;

    private String productName;

    @ApiModelProperty(value="")
    private Integer productId;

    @ApiModelProperty(value="")
    private Integer num;

    /**
     * 总价
     */
    @ApiModelProperty(value="总价")
    private BigDecimal totalPrice;

    /**
     * 单价
     */
    @TableField(value = "sell_price")
    @ApiModelProperty(value="单价")
    private BigDecimal sellPrice;

}