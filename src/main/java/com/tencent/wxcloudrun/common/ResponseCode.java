package com.tencent.wxcloudrun.common;
/**
 * 枚举类
 */
public enum ResponseCode {
    /**
     * 成功，失败，未登录（需要登录），无权限，系统业务错误
     * @Params
     */
    SUCCESS(200,"SUCCESS"),
    ERROR(400,"ERROR"),
    NEED_LOGIN(403,"FORBIDDEN"),
    Unauthorized(401,"UNAUTHORIZED"),
    BUSINESS_ERROR(500,"BUSINESS_ERROR");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
