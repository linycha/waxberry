package com.tencent.wxcloudrun.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.modules.dto.CartDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    IPage<CartDTO> selectPage(@Param("page") IPage<Object> page, @Param("dto") QueryDTO dto);
    int addCartNum(Cart cart);
    int minusCartNum(Cart cart);
    int updateNum(Cart cart);
}