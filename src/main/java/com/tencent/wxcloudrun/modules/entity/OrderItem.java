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

import java.math.BigDecimal;
import java.util.Date;

/**
    * 订单商品表
    */
@ApiModel(value="订单商品表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order_item")
public class OrderItem {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "order_no")
    @ApiModelProperty(value="")
    private String orderNo;

    @TableField(exist = false)
    private String productName;

    @TableField(exist = false)
    private String logoImg;

    @TableField(value = "product_id")
    @ApiModelProperty(value="")
    private Integer productId;

    @TableField(value = "num")
    @ApiModelProperty(value="")
    private Integer num;

    /**
     * 总价
     */
    @TableField(value = "total_price")
    @ApiModelProperty(value="总价")
    private BigDecimal totalPrice;

    /**
     * 单价
     */
    @TableField(value = "sell_price")
    @ApiModelProperty(value="单价")
    private BigDecimal sellPrice;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_NUM = "num";

    public static final String COL_TOTAL_PRICE = "total_price";

    public static final String COL_SELL_PRICE = "sell_price";

    public static final String COL_CREATE_TIME = "create_time";
}