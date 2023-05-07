package com.tencent.wxcloudrun.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.modules.dto.OrderInfoDTO;
import com.tencent.wxcloudrun.modules.dto.OrderStatusCountDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    IPage<OrderInfoDTO> selectPage(@Param("page") IPage<Object> page, @Param("dto") QueryDTO dto);

    int updateStatusByOrderNo(Order order);

    OrderStatusCountDTO selectStatusCount(@Param("userId") String userId);
}