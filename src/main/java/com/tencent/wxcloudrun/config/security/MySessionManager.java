package com.tencent.wxcloudrun.config.security;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义sessionManager，服务端分配一个token，用户权限校验，
 * 登录成功存储一个token,每次访问接口就校验这个token
 */
@Slf4j
public class MySessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "aaaacl-token";

    public MySessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        //从请求头获取token
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if(StrUtil.isNotBlank(token)){
            log.info("shiro权限校验拦截token："+token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }else{
            log.info("shiro权限校验拦截：token is null为空！");
            //return null;
            return super.getSessionId(request,response);
        }

    }

    public static void main(String[] args) {
        System.out.println(StrUtil.isBlank("d9a3eaf3-00d2-4fd6-9bd1-45bff30f5244"));
    }

    /**
     * 去掉没有访问权限时跳转的url上的jSessionId
     * @param session
     * @param context
     */
    @Override
    protected void onStart(Session session, SessionContext context) {
        super.onStart(session, context);
        ServletRequest request = WebUtils.getRequest(context);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
    }
}
