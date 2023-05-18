package com.tencent.wxcloudrun.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.modules.dto.CartDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.LoginRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginRecordMapper extends BaseMapper<LoginRecord> {
    IPage<LoginRecord> selectPage(@Param("page") IPage<Object> page, @Param("dto") QueryDTO dto);

}