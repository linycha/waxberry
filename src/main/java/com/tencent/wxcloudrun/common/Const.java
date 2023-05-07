package com.tencent.wxcloudrun.common;

/**
 * 一些常量
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final Integer PAGE_DEFAULT_NUM = 1;
    public static final Integer PAGE_DEFAULT_SIZE = 10;
    /**
     * 用户账号状态：0：禁用，1：正常
     */
    public static final Integer USER_STATUS_ENABLE = 1;
    public static final Integer USER_STATUS_DISABLE = 0;

    /**
     * 角色
     */
    public static final String USER_ROLE_NORMAL = "normal";
    public static final String USER_ROLE_ADMIN = "admin";

    /**
     * 微信
     * @param current
     * @param size
     */
    public static final String WECHAT_USERNAME = "wechatUser";
    public static final String TOKEN_PRE = "tourist:";
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 订单状态
     * @param current
     * @param size
     */

    public static final String ORDER_STATUS_SUBMIT = "1";
    public static final String ORDER_STATUS_PAY = "2";
    public static final String ORDER_STATUS_DELIVER= "3";
    public static final String ORDER_STATUS_RECEIVING= "4";
    public static final String ORDER_STATUS_COMPLETE = "5";
    public static final String ORDER_STATUS_COMMENT = "6";
    public static final String ORDER_STATUS_CANCEL = "0";
    public static final String ORDER_STATUS_RETURN = "20";



    public static void initPage(int current, int size){

    }
    public static long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime + currentTime%10;
    }
}
