package com.tencent.wxcloudrun.modules.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.modules.dao.OrderItemMapper;
import com.tencent.wxcloudrun.modules.entity.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends ServiceImpl<OrderItemMapper, OrderItem> {

}
