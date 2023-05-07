package com.tencent.wxcloudrun.modules.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.common.utils.FileUploadUtil;
import com.tencent.wxcloudrun.common.utils.UserUtils;
import com.tencent.wxcloudrun.modules.dto.PasswordDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.dto.UserDTO;
import com.tencent.wxcloudrun.modules.entity.User;
import com.tencent.wxcloudrun.modules.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @date 2022/11/18 15:10
 */
@RestController
@CrossOrigin(origins="*",maxAge=3600)
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Resource
    private FileUploadUtil fileUploadUtil;
    /**
     * 获取用户个人信息
     */
    @GetMapping("info")
    public Res<User> info(){
        User user = userService.selectByUsername(null, UserUtils.getUserId());
        if(user == null){
            return Res.errorMsg("找不到当前用户");
        }
        return Res.success(user);
    }

    @GetMapping("list")
    public Res<IPage<User>> list(QueryDTO dto){
        return Res.success(userService.selectPage(dto));
    }

    /**
     * 根据id值在表里是否存在判断是新增还是修改操作
     * @param entity
     * @return
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody User entity){
        return userService.saveOrUpdateUser(entity);
    }

    @PutMapping("update_mobile")
    public Res<String> updateMobile(String mobile){
        User user = new User();
        user.setId(UserUtils.getUserPrimaryId());
        user.setMobile(mobile);
        return userService.updateMobile(user);
    }

    /**
     * 修改用户名
     * @param username
     * @return
     */
    @PutMapping("update_name")
    public Res<String> updateName(@RequestBody String username){
        if(StringUtils.isBlank(username)){
            return Res.errorMsg("用户名不能为空");
        }
        return userService.updateUsername(User.builder()
                .id(UserUtils.getUserPrimaryId()).username(username).build());
    }


    @PutMapping("update_head")
    @ApiOperation("修改个人头像")
    public Res<String> updateHead(MultipartFile file) throws IOException {
        if(file == null){
            return Res.errorMsg("上传头像为空");
        }
        User user = new User();
        user.setId(UserUtils.getUserPrimaryId());
        String newFileName = fileUploadUtil.uploadFile(file);
        user.setAvatar(fileUploadUtil.url + newFileName);
        return userService.saveOrUpdateUser(user);
    }

    /**
     * 修改用户个人密码
     * @return
     */
    @PutMapping("updatePwd")
    @ApiOperation("修改用户个人密码")
    public Res<String> updatePassword(@RequestBody PasswordDTO dto){
        return userService.updatePassword(dto);
    }

    /**
     * 修改用户角色
     * @param dto
     * @return
     */
    @PutMapping("updateRole")
    public Res<String> updateRole(@RequestBody UserDTO dto){
        int result = userService.updateRole(dto);
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }
}
