package com.tencent.wxcloudrun.modules.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxSessionDTO implements Serializable {

    private String appId;

    private String sessionKey;

    private String signature;

    private String rawData;

    private String encryptedData;

    private String iv;

    /**
     * 解密openId的code
     */
    private String openIdCode;

    /**
     * 解密手机号的code
     */
    private String phoneCode;

    private String userId;
}
