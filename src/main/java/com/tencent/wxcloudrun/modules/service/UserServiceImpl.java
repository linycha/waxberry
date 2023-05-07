package com.tencent.wxcloudrun.modules.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.common.Const;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.common.utils.CheckUtil;
import com.tencent.wxcloudrun.common.utils.UserUtils;
import com.tencent.wxcloudrun.modules.dao.UserMapper;
import com.tencent.wxcloudrun.modules.dto.PasswordDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.dto.UserDTO;
import com.tencent.wxcloudrun.modules.entity.Role;
import com.tencent.wxcloudrun.modules.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {

    public Res<String> insertRegister(User user){
        // 生成用户id
        user.setUserId(IdUtil.fastSimpleUUID());
        int i = baseMapper.checkUsername(user.getUsername());
        if(i > 0){
            return Res.errorMsg("该用户名已存在");
        }
        if(!CheckUtil.isMobile(user.getMobile())){
            return Res.errorMsg("手机号格式错误");
        }
        int resultCount = baseMapper.checkMobile(user.getMobile());
        if(resultCount == 1){
            return Res.errorMsg("该手机号已被注册");
        }
        //保存加密完的密码
        String md5Password = UserUtils.hashTwo(user.getPassword());
        user.setPassword(md5Password);
        int result = baseMapper.insertSelective(user);
        if(result == 0) {
            return Res.errorMsg("注册失败");
        }
        // 添加普通用户角色
        baseMapper.insertRole(user.getUserId(),1);
        return Res.successMsg("注册成功");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(IdUtil.fastSimpleUUID());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public User registerWechatUser(User user){
        log.info("开始注册微信用户：openId"+user.toString());

        // 生成用户id
        user.setUserId(IdUtil.fastSimpleUUID());
        //保存加密完的密码
        String md5Password = UserUtils.hashTwo("123456");
        user.setPassword(md5Password);
        boolean result = save(user);

        if(!result) {
            log.info("注册失败：openId"+user.toString());
        }
        // 添加普通用户角色
        baseMapper.insertRole(user.getUserId(),1);
        return user;
    }

    public Res<String> updateMobile(User user){
        int result = baseMapper.checkMobile(user.getMobile());
        if(result != 1){
            return Res.errorMsg("手机号已存在");
        }
        result = baseMapper.updateByPrimaryKeySelective(user);
        if(result >0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }

    public Res<String> updatePassword(PasswordDTO dto){
        User user = baseMapper.selectByPrimaryKey(UserUtils.getUserId());
        String oldPwd = UserUtils.hashTwo(dto.getOldPassword());
        if(!oldPwd.equals(user.getPassword())){
            return Res.errorMsg("输入的旧密码错误");
        }
        int result = baseMapper.updateByPrimaryKeySelective(
                User.builder().id(UserUtils.getUserPrimaryId()).
                        password(UserUtils.hashTwo(dto.getNewPassword())).build()
        );
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }


    /**
     * 校验用户名和email是否存在
     * */

    public Res<String> checkValid(String str, String type){
        if(StringUtils.isNotBlank(type)){
            if(Const.USERNAME.equals(type)){
                int resultCount = baseMapper.checkUsername(str);
                if(resultCount > 0){
                    return Res.errorMsg("用户名已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int resultCount = baseMapper.checkMobile(str);
                if(resultCount > 0){
                    return Res.errorMsg("手机号码已存在");
                }
            }
        }else{
            return Res.errorMsg("参数错误");
        }
        return Res.successMsg("校验成功");
    }


    public User selectByUsername(String username,String userId){
        User user = baseMapper.selectByUsernameOrUserId(username,userId);
        if(user != null){
            user.setRoles(user.getRoleList().stream().map(Role::getName).toArray(String[]::new));
            String encryptMobile = user.getMobile().substring(0,3)+"****"+user.getMobile().substring(7);
            user.setEncryptMobile(encryptMobile);
        }
        return user;
    }

    public User selectByMobile(String mobile){
        User user = baseMapper.selectByMobile(mobile);
        if(user != null){
            user.setRoles(user.getRoleList().stream().map(Role::getName).toArray(String[]::new));
        }
        return user;
    }

    public String selectUsernameByMobile(String mobile){
        return baseMapper.selectUsernameByMobile(mobile);
    }


    public Res<String> updateUsername(User user) {
        int count = baseMapper.checkUsername(user.getUsername());
        if(count > 0){
            return Res.errorMsg("用户名已存在重复");
        }
        int result = baseMapper.updateByPrimaryKeySelective(user);
        if(result == 0){
            return Res.errorMsg("修改用户名失败");
        }
        return Res.successMsg("修改用户名成功");
    }

    public IPage<User> selectPage(QueryDTO dto) {
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());

        return baseMapper.selectPage(page, dto);
    }


    public Res<String> saveOrUpdateUser(User user) {
        if(user.getId() != null){
            user.setPassword(null);
            baseMapper.updateByPrimaryKeySelective(user);
        }else {
            // 新增的时候才校验用户名手机号
            int count = baseMapper.checkMobile(user.getMobile());
            if(count > 0) {
                return Res.errorMsg("手机号已存在");
            }
            int result = baseMapper.checkUsername(user.getUsername());
            if(result > 0) {
                return Res.errorMsg("用户名已存在");
            }
            // 初始密码123456
            user.setPassword(UserUtils.hashTwo("123456"));
            baseMapper.insertSelective(user);
            // 默认角色
            //baseMapper.insertRole(user.getId(), 1);
        }

        return Res.successMsg("新增或修改成功");
    }


    public int updateRole(UserDTO dto) {
        return baseMapper.updateRole(dto);
    }

}
