package com.tencent.wxcloudrun.modules.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tencent.wxcloudrun.modules.entity.OrderItem;
import com.tencent.wxcloudrun.modules.entity.Shipping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
    * 订单信息表
    */
@ApiModel(value="订单信息表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`order`")
public class OrderInfoDTO {
    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="订单id")
    private Integer id;
    private Integer productId;
    private Integer cartId;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    @ApiModelProperty(value="订单号")
    private String orderNo;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private String userId;

    /**
     * 收货地址id
     */
    @TableField(value = "shipping_id")
    @ApiModelProperty(value="收货地址id")
    private Integer shippingId;

    /**
     * 店铺id
     */
    @TableField(value = "shop_id")
    @ApiModelProperty(value="店铺id")
    private Integer shopId;

    /**
     * 餐盒费
     */
    @TableField(value = "box_cost")
    @ApiModelProperty(value="餐盒费")
    private BigDecimal boxCost;

    /**
     * 配送费,单位是元
     */
    @TableField(value = "send_cost")
    @ApiModelProperty(value="配送费,单位是元")
    private BigDecimal sendCost;

    /**
     * 总价
     */
    @TableField(value = "total_price")
    @ApiModelProperty(value="总价")
    private BigDecimal totalPrice;

    /**
     * 实际付款金额,单位是元,保留两位小数
     */
    @TableField(value = "pay_money")
    @ApiModelProperty(value="实际付款金额,单位是元,保留两位小数")
    private BigDecimal payMoney;

    /**
     * 支付类型,1-微信支付,2支付宝支付3,到付
     */
    @TableField(value = "pay_type")
    @ApiModelProperty(value="支付类型,1-微信支付,2支付宝支付3,到付")
    private String payType;

    @TableField(value = "shipping_method")
    @ApiModelProperty(value="配送方式")
    private String shippingMethod;

    /**
     * 订单备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="订单备注")
    private String remark;
    private String address;
    private String productName;
    private String completeTime;
    private String shopName;
    private String shippingAddress;
    private String shippingName;
    private List<OrderItem> itemList;
    private Shipping shipping;
    /**
     * 拼接商品名称价格数量
     */
    private String productStr;
    private int totalNum;

    /**
     * 订单状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="订单状态")
    private String status;

    private Integer num;

    private String sellPrice;
    private String username;
    private String backAddress;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

}