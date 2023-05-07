package com.tencent.wxcloudrun.modules.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 */
@Data
public class UserDTO implements Serializable {
    private Integer userId;
    private Integer roleId;
}
