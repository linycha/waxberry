package com.tencent.wxcloudrun.modules.controller;

import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.modules.dto.WxSessionDTO;
import com.tencent.wxcloudrun.modules.service.WechatLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 */
@RestController
@RequestMapping("wechat")
@Api(tags = "微信小程序登录相关接口")
public class WechatLoginController {

    @Resource
    private WechatLoginService wechatLoginService;

    /**
     * 返回生成的用户id
     * @param code
     * @return
     * @throws WxErrorException
     */
    @GetMapping("login")
    @ApiOperation("获取openId并登录，返回token，未注册返回空")
    public Res<String> login(String code,HttpServletRequest request) throws WxErrorException {
        return Res.success(wechatLoginService.login(code,request));
    }

    @GetMapping("loginByPhone")
    @ApiOperation("获取用户手机号，并认证登录")
    public Res<String> loginByPhone(WxSessionDTO dto, HttpServletRequest request) throws WxErrorException {
/*        String token = WebUtils.toHttp(request).getHeader("aaaacl-token");
        dto.setUserId(token);*/
        return Res.success(wechatLoginService.loginByPhone(dto,request));
    }

    @PostMapping("getUserInfo")
    @ApiOperation("获取用户信息")
    public Res<String> getUserInfo(@RequestBody WxSessionDTO dto) throws WxErrorException {
        wechatLoginService.getUserInfo(dto);
        return null;
    }
}
