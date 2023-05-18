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
    * 登录日志表
    */
@ApiModel(value="登录日志表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "login_record")
public class LoginRecord implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private String userId;

    @TableField(value = "open_id")
    @ApiModelProperty(value="")
    private String openId;

    @TableField(value = "mobile")
    @ApiModelProperty(value="")
    private String mobile;

    @TableField(value = "ip_address")
    @ApiModelProperty(value="")
    private String ipAddress;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_OPEN_ID = "open_id";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_IP_ADDRESS = "ip_address";

    public static final String COL_CREATE_TIME = "create_time";
}