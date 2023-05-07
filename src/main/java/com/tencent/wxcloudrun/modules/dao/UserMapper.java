package com.tencent.wxcloudrun.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.dto.UserDTO;
import com.tencent.wxcloudrun.modules.entity.Role;
import com.tencent.wxcloudrun.modules.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    User selectByUsernameOrUserId(@Param("username") String username,@Param("userId") String userId);
    User selectByMobile(@Param("mobile") String mobile);

    /**
     * 通过用户id获取对应角色信息
     * @param userId
     * @return
     */
    Role selectRoleByUserId(String userId);
    /**
     * 添加用户的角色
     */
    int insertRole(@Param("userId")String userId,@Param("roleId")Integer roleId);
    int updateByPrimaryKey(User record);
    int checkUsername(String username);
    int checkMobile(String mobile);
    User selectLogin(@Param("username")String username,@Param("password")String password);
    int updatePasswordByUsername(@Param("username")String username,@Param("password")String password);
    int checkPassword(@Param("password")String password,@Param("userId")String userId);
    IPage<User> selectPage(@Param("page") IPage<Object> page, @Param("dto") QueryDTO dto);
    String selectUsernameByMobile(String phone);
    int updateRole(UserDTO dto);

}