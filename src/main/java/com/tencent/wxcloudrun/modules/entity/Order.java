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
    * 订单信息表
    */
@ApiModel(value="订单信息表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`order`")
public class Order {
    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="订单id")
    private Integer id;

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
     * 餐盒费/包装费
     */
    @TableField(value = "box_cost")
    @ApiModelProperty(value="餐盒费/包装费")
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

    /**
     * 订单备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="订单备注")
    private String remark;

    /**
     * 订单送达时间
     */
    @TableField(value = "complete_time")
    @ApiModelProperty(value="订单送达时间")
    private String completeTime;

    /**
     * 订单状态：1：已提交，2：已支付，3：已发货，4：已收货，5：已完成，6：已评价，20：申请退货，21：已退货。
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="订单状态：1：已提交，2：已支付，3：已发货，4：已收货，5：已完成，6：已评价，20：申请退货，21：已退货。")
    private String status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 1：专车配送，2：顺丰快递，3：其他
     */
    @TableField(value = "shipping_method")
    @ApiModelProperty(value="1：专车配送，2：顺丰快递，3：其他")
    private String shippingMethod;

    @TableField(value = "shipping_address")
    private String shippingAddress;

    @TableField(value = "shipping_name")
    private String shippingName;

    @TableField(value = "back_address")
    private String backAddress;


    /**
     * 删除标记
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="删除标记")
    private Boolean delFlag;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SHIPPING_ID = "shipping_id";

    public static final String COL_SHOP_ID = "shop_id";

    public static final String COL_BOX_COST = "box_cost";

    public static final String COL_SEND_COST = "send_cost";

    public static final String COL_TOTAL_MONEY = "total_money";

    public static final String COL_PAY_MONEY = "pay_money";

    public static final String COL_PAY_TYPE = "pay_type";

    public static final String COL_REMARK = "remark";

    public static final String COL_COMPLETE_TIME = "complete_time";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_SHIPPING_METHOD = "shipping_method";
}