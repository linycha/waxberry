package com.tencent.wxcloudrun.modules.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
    * 帖子内容表
    */
@ApiModel(value="帖子内容表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "posts")
public class PostsDTO {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private Integer userId;

    /**
     * 标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value="标题")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="内容")
    private String content;

    /**
     * 图片地址
     */
    @TableField(value = "img_url")
    @ApiModelProperty(value="图片地址")
    private String imgUrl;

    /**
     * 状态：0：审核中，1：已通过，2：已禁用，3：内容违规
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态：0：审核中，1：已通过，2：已禁用，3：内容违规")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 提交时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="提交时间")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后一次更新时间")
    private Date updateTime;

    /**
     * 删除状态
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="删除状态")
    private Boolean delFlag;

    @TableField(exist = false)
    private String username;

    private MultipartFile file;

}