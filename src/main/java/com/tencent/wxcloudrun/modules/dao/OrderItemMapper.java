package com.tencent.wxcloudrun.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Feedback;
import com.tencent.wxcloudrun.modules.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    IPage<Feedback> selectPage(@Param("page") IPage<Object> page, @Param("dto") QueryDTO dto);
}