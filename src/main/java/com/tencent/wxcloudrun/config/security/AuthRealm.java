package com.tencent.wxcloudrun.config.security;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.common.Const;
import com.tencent.wxcloudrun.modules.entity.Role;
import com.tencent.wxcloudrun.modules.entity.User;
import com.tencent.wxcloudrun.modules.service.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Realm
 */
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private UserServiceImpl userService;
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权 doGetAuthorizationInfo");
        //从session里拿user,查找该用户有哪些角色、权限
        Object o = principals.fromRealm(this.getClass().getName()).iterator().next();
        //BeanUtils.copyProperties(o,user);
        //User user = userService.selectByUsername(null,o.toString());
        User user = JSON.parseObject(o.toString(),User.class);
        /*之前里的方法
        User u = (User)principals.getPrimaryPrincipal();
        User user = userService.selectByUsername(u.getUsername());*/

        List<String> permissionList = new ArrayList<>();
        List<Role> roleList = user.getRoleList();
        List<String> roleNameList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)){
            for(int i = 0;i<roleList.size();i++){
                Object o1 = roleList.get(i);
                Role role = new Role();
                BeanUtils.copyProperties(o1,role);
                roleNameList.add(role.getName());
            }
        }
        SimpleAuthorizationInfo info  = new SimpleAuthorizationInfo();
        //info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }
    /**
     * 认证登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenToken) throws AuthenticationException {

        System.out.println("开始认证 doGetAuthenticationInfo");
        //从token中获取用户信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenToken;
        String username = token.getUsername();
        User user = new User();

        //判断是手机号登陆还是用户名,手机号为微信用户登录
        if(StringUtils.isNumeric(username)){
            user = userService.selectByMobile(username);
        }else {
            user = userService.selectByUsername(username,null);
        }
        if(user == null){
            throw new UnknownAccountException();
        }
        if(Const.USER_STATUS_DISABLE.equals(user.getStatus())){
            throw new LockedAccountException("该账号已被禁用，请联系管理员");
        }
        String[] roles = user.getRoleList().stream().map(Role::getName).toArray(String[]::new);
        user.setRoles(roles);
        String str = JSON.toJSONString(user);
        return new SimpleAuthenticationInfo(str, user.getPassword(), this.getClass().getName());
    }
}