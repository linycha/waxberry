package com.tencent.wxcloudrun.config;


import com.tencent.wxcloudrun.config.security.AuthRealm;
import com.tencent.wxcloudrun.config.security.MyRolesAuthorizationFilter;
import com.tencent.wxcloudrun.config.security.MySessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro主要配置类
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager){
        System.out.println("执行 ShiroFilterFactoryBean.shiroFilter");

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        //没有登录时跳转的url
        bean.setLoginUrl("/to_login");
        //bean.setSuccessUrl("/login");
        //没有权限访问某个页面的时候跳转的url
        bean.setUnauthorizedUrl("/unauthc");
        //自定filter,满足一个角色既可通过
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("role",new MyRolesAuthorizationFilter());
        bean.setFilters(filterMap);

        //某些请求该如何拦截，要定义LinkHashMap
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //过滤是从上而下顺序执行，一般/**放到最下面
        // anon不需要做任何校验,authc只有登录用户才可以访问
        filterChainDefinitionMap.put("/logout","anon");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/register","anon");
        filterChainDefinitionMap.put("/to_login","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/druid/**","anon");
        filterChainDefinitionMap.put("/user/**","authc");
        filterChainDefinitionMap.put("/cart/**","authc");
        filterChainDefinitionMap.put("/order/**","authc");
        filterChainDefinitionMap.put("/shipping/**","authc");

        filterChainDefinitionMap.put("/**","anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //前后端分离设置sessionManager
        manager.setSessionManager(sessionManager());
        //manager.setCacheManager(cacheManager());
        manager.setRealm(authRealm());
        return manager;
    }

    /**
     * 设置自定义加密器credentialsMatcher
     */

    @Bean()
    public AuthRealm authRealm(){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return authRealm;
    }
    @Bean
    public MySessionManager sessionManager(){
        MySessionManager sessionManager = new MySessionManager();
        //会话超时时间，单位毫秒，60分钟
        sessionManager.setGlobalSessionTimeout(3*60*60*1000);
        //去掉url上的jSessionId
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //session持久化
        //sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        System.out.println("开始密码加解密");
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置散列算法，使用MD5算法,散列2次
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }
    /**
     * 配置redisManager
     */
/*    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("47.99.71.179");
        redisManager.setPort(6379);
        return redisManager;
    }
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置过期时间，单位s
        redisCacheManager.setExpire(120);
        return  redisCacheManager;
    }

    *//**
     * 自定义session持久化
     * @return
     *//*
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(getRedisManager());
        return sessionDAO;
    }*/

    /**
     * 配置shiro与spring的关联，开启AOP注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return  advisor;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return  creator;
    }

}
