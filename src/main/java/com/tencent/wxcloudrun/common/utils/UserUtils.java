package com.tencent.wxcloudrun.common.utils;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.modules.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 */
public class UserUtils {
    /**
     * 获取当前登录用户信息
     * @return user
     */
    public static User getUser() {
        Object o = SecurityUtils.getSubject().getPrincipal();
        //解析user的json字符串
        return JSON.parseObject(o.toString(),User.class);
    }

    /**
     * 获取当前登录用户Id
     * @return userId
     */
    public static String getUserId() {
        return getUser().getUserId();
    }

    public static String getOpenId() {
        return getUser().getOpenId();
    }

    public static Integer getUserPrimaryId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户首个角色,如 “admin”
     */
    public static String getRole(){
        return getUser().getRoles()[0];
    }

    /**
     * 利用shiro的api加密密码
     * @param password
     * @return
     */
    public static String hashTwo(String password){
        Object md5Password = new SimpleHash("md5",password, null, 2);
        return md5Password.toString();
    }

}
