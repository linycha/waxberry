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
    * 收货地址表
    */
@ApiModel(value="收货地址表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shipping")
public class Shipping {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private String userId;

    /**
     * 收货姓名
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="收货姓名")
    private String name;

    /**
     * 收货移动电话
     */
    @TableField(value = "tel")
    @ApiModelProperty(value="收货移动电话")
    private String tel;

    /**
     * 省份
     */
    @TableField(value = "province")
    @ApiModelProperty(value="省份")
    private String province;

    /**
     * 城市
     */
    @TableField(value = "city")
    @ApiModelProperty(value="城市")
    private String city;

    /**
     * 区/县
     */
    @TableField(value = "county")
    @ApiModelProperty(value="区/县")
    private String county;

    /**
     * 区域编码
     */
    @TableField(value = "area_code")
    @ApiModelProperty(value="区域编码")
    private String areaCode;

    /**
     * 详细地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value="详细地址")
    private String address;

    /**
     * 是否默认地址：0不是，1是
     */
    @TableField(value = "is_default")
    @ApiModelProperty(value="是否默认地址：0不是，1是")
    private String isDefault;

    /**
     * 删除标记：0未删，1已删
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="删除标记：0未删，1已删")
    private Boolean delFlag;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    @TableField(value = "update_time")
    @ApiModelProperty(value="")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_NAME = "name";

    public static final String COL_TEL = "tel";

    public static final String COL_PROVINCE = "province";

    public static final String COL_CITY = "city";

    public static final String COL_COUNTY = "county";

    public static final String COL_AREA_CODE = "area_code";

    public static final String COL_ADDRESS = "address";

    public static final String COL_IS_DEFAULT = "is_default";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}