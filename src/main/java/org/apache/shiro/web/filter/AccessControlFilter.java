package org.apache.shiro.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@Slf4j
public abstract class AccessControlFilter extends PathMatchingFilter {
    public static final String DEFAULT_LOGIN_URL = "/login.jsp";
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    private String loginUrl = "/login.jsp";

    public AccessControlFilter() {
    }

    public String getLoginUrl() {
        return this.loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    protected abstract boolean isAccessAllowed(ServletRequest var1, ServletResponse var2, Object var3) throws Exception;

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return this.onAccessDenied(request, response);
    }

    protected abstract boolean onAccessDenied(ServletRequest var1, ServletResponse var2) throws Exception;

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return this.isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
    }

    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request);
    }

    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        this.saveRequest(request);
        this.redirectToLogin(request, response);
    }

    protected void saveRequest(ServletRequest request) {
        WebUtils.saveRequest(request);
    }

    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        log.info("shiro拦截当前用户未登录或已过期, 返回403");
        PrintWriter writer = null;
        JSONObject result = new JSONObject();
        result.put("code", ResponseCode.NEED_LOGIN.getCode());
        result.put("msg", "未登录或登录过期");
        result.put("data", "");
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        writer = response.getWriter();
        writer.print(result);
/*        String loginUrl = this.getLoginUrl();
        WebUtils.issueRedirect(request, response, loginUrl);*/
    }
}
