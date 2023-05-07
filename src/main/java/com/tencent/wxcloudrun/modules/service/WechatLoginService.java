package com.tencent.wxcloudrun.modules.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.Const;
import com.tencent.wxcloudrun.modules.dto.WxSessionDTO;
import com.tencent.wxcloudrun.modules.entity.User;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class WechatLoginService {

    @Resource
    private WxMaService wxMaService;

    @Resource
    private UserServiceImpl userService;

    /**
     * 登录获取openId和sessionKey，未注册返回空，已注册自动登录并返回token
     * @param code code
     */
    public String login(String code) throws WxErrorException {
        if(StrUtil.isBlank(code)) {
            log.error("微信小程序登录获取openId和sessionKey: code不能为空");
            throw new IllegalArgumentException("code不能为空");
        }
        WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", result.getOpenid());
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return "";
        }else {
            return shiroLogin(user);
        }
    }

    /**
     * 登录获取openId和sessionKey，新用户注册保存到数据库，老用户返回生成的用户id
     * @param code code
     */
    public User register(String code) throws WxErrorException {
        if(StrUtil.isBlank(code)) {
            log.error("微信小程序登录获取openId和sessionKey: code不能为空");
            throw new IllegalArgumentException("code不能为空");
        }
        WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", result.getOpenid());
        User user = userService.getOne(queryWrapper);
        if(user == null){
            user = userService.registerWechatUser(User.builder().openId(result.getOpenid()).build());
        }
        return user;
    }

    public String loginByPhone(WxSessionDTO dto) throws WxErrorException {
        log.info("微信小程序---开始手机号认证登录:"+dto.getUserId());
        if(StrUtil.isBlank(dto.getOpenIdCode()) || StrUtil.isBlank(dto.getPhoneCode())) {
            log.error("微信小程序---开始手机号认证登录: code不能为空");
            throw new IllegalArgumentException("code不能为空");
        }
        // 用户信息校验
/*        if (!wxMaService.getUserService().checkUserInfo(dto.getSessionKey(), dto.getRawData(), dto.getSignature())) {
            WxMaConfigHolder.remove();//清理ThreadLocal
            throw new IllegalArgumentException("用户信息校验失败");

        }*/
        User user = register(dto.getOpenIdCode());
        // 解密code获取手机号
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(dto.getPhoneCode());
        if(phoneNoInfo != null && StrUtil.isNotBlank(phoneNoInfo.getPurePhoneNumber())){
            if(user != null){
                // 修改保存手机号
                user.setMobile(phoneNoInfo.getPurePhoneNumber());
                userService.updateById(user);

                //shiro登录获取token
                return shiroLogin(user);
            }else {
                log.error("微信小程序手机号认证登录：该用户不存在");
            }
        }
        System.out.println(phoneNoInfo);
        return "";
    }

    /**
     * shiro登录 返回token
     * @param user
     * @return
     */
    public String shiroLogin(User user) {
        Subject subject = SecurityUtils.getSubject();
        try {
            // 手机号登录
            UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), Const.DEFAULT_PASSWORD);
            subject.login(token);
/*            info.put("token",subject.getSession().getId());
            info.put("userId", UserUtils.getUserId());*/
            return subject.getSession().getId().toString();
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            e.printStackTrace();
            log.error("用户名或密码错误");
        } catch (LockedAccountException e){
            e.printStackTrace();
            log.error("该账号已被禁用，请联系管理员");
        }catch (AuthenticationException e){
            e.printStackTrace();
            log.error("登录异常，请联系管理员");
        }
        return "";
    }

    /**
     * 获取用户信息，新版本接口已不支持获取用户信息
     * @param dto
     * @return
     */
    public String getUserInfo(WxSessionDTO dto) {

        // 解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(dto.getSessionKey(), dto.getEncryptedData(), dto.getIv());
        WxMaConfigHolder.remove();//清理ThreadLocal
        System.out.println(userInfo);
        return "";
    }
}
