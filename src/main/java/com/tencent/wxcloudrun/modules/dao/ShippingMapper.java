package com.tencent.wxcloudrun.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Shipping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShippingMapper extends BaseMapper<Shipping> {
    IPage<Shipping> selectPage(@Param("page") IPage<Object> page, @Param("dto") QueryDTO dto);

    int updateIsDefault(Shipping shipping);

}