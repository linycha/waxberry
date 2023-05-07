package com.tencent.wxcloudrun.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    IPage<ProductCategory> selectPage(@Param("page") IPage<Object> page, @Param("dto") QueryDTO dto);
}