package com.tencent.wxcloudrun.modules.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @Description TODO
 */
@Data
public class PasswordDTO implements Serializable {
    private static final long serialVersionUID = -4496599659421192593L;
    private String oldPassword;

    private String newPassword;
}
