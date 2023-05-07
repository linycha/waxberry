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
    * 轮播图信息表
    */
@ApiModel(value="轮播图信息表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "viewpager")
public class Viewpager {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "article_id")
    @ApiModelProperty(value="")
    private String articleId;

    @TableField(value = "title")
    @ApiModelProperty(value="")
    private String title;

    @TableField(value = "content")
    @ApiModelProperty(value="")
    private String content;

    @TableField(value = "img_url")
    @ApiModelProperty(value="")
    private String imgUrl;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态")
    private String status;

    /**
     * 排序
     */
    @TableField(value = "sorts")
    @ApiModelProperty(value="排序")
    private Integer sorts;

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
     * 删除标记
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="删除标记")
    private Boolean delFlag;

    public static final String COL_ID = "id";

    public static final String COL_ARTICLE_ID = "article_id";

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_IMG_URL = "img_url";

    public static final String COL_REMARK = "remark";

    public static final String COL_STATUS = "status";

    public static final String COL_SORTS = "sorts";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DEL_FLAG = "del_flag";
}