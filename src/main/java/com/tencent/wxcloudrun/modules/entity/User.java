package com.tencent.wxcloudrun.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"updateTime"})
@TableName(value = "sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 7321352169002894594L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "password")
    private String password;

    @TableField(value = "sex")
    private Integer sex;

    @TableField(value = "mobile")
    private String mobile;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private Date updateTime;

    @TableField(exist = false)
    private String[] roles;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private String encryptMobile;

    @TableField(exist = false)
    private List<Role> roleList = new ArrayList<>();

    @TableField(value = "true_name")
    private String trueName;

    @TableField(value = "open_id")
    private String openId;

}