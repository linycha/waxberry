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
    * 意见反馈表
    */
@ApiModel(value="意见反馈表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "feedback")
public class Feedback {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private Integer userId;

    @TableField(value = "content")
    @ApiModelProperty(value="")
    private String content;

    /**
     * 反馈意见类别：0:日常 1：系统，2：使用
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="反馈意见类别：0:日常 1：系统，2：使用")
    private String type;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_TYPE = "type";

    public static final String COL_CREATE_TIME = "create_time";
}