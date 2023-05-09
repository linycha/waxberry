package com.tencent.wxcloudrun.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 字典信息表
    */
@ApiModel(value="字典信息表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict")
public class Dict implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "code")
    @ApiModelProperty(value="")
    private String code;

    @TableField(value = "value_one")
    @ApiModelProperty(value="")
    private String valueOne;

    @TableField(value = "value_two")
    @ApiModelProperty(value="")
    private String valueTwo;

    @TableField(value = "value_three")
    @ApiModelProperty(value="")
    private String valueThree;

    @TableField(value = "value_four")
    @ApiModelProperty(value="")
    private String valueFour;

    @TableField(value = "value_five")
    @ApiModelProperty(value="")
    private String valueFive;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后一次更新时间")
    private Date updateTime;

    /**
     * 删除标记
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="删除标记")
    private Boolean delFlag;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CODE = "code";

    public static final String COL_VALUE_ONE = "value_one";

    public static final String COL_VALUE_TWO = "value_two";

    public static final String COL_VALUE_THREE = "value_three";

    public static final String COL_VALUE_FOUR = "value_four";

    public static final String COL_VALUE_FIVE = "value_five";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_REMARK = "remark";
}