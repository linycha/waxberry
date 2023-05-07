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

@ApiModel(value="product_category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product_category")
public class ProductCategory {
    /**
     * 类别Id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="类别Id")
    private Integer id;

    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value="父类别id当id=0时说明是根节点,一级类别")
    private Integer parentId;

    /**
     * 类别名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="类别名称")
    private String name;

    /**
     * 类别状态1-正常,0-已废弃
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="类别状态1-正常,0-已废弃")
    private String status;

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="排序编号,同类展示顺序,数值相等则自然排序")
    private Integer sort;

    @TableField(value = "del_flag")
    @ApiModelProperty(value="")
    private Boolean delFlag;

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

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    public static final String COL_ID = "id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_NAME = "name";

    public static final String COL_STATUS = "status";

    public static final String COL_SORT = "sort";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_REMARK = "remark";
}