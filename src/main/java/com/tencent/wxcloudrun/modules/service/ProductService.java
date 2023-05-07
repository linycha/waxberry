package com.tencent.wxcloudrun.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.modules.dao.ProductMapper;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {
    public IPage<Product> selectPage(QueryDTO dto) {
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());
        IPage<Product> result = baseMapper.selectPage(page, dto);
        result.getRecords().forEach(e -> {
            if (StringUtils.isNotBlank(e.getSubImg())) {
                e.setSubImgList(Arrays.asList(e.getSubImg().split(",")));
            }
            if (StringUtils.isNotBlank(e.getDetailImg())) {
                e.setDetailList(Arrays.asList(e.getDetailImg().split(",")));
            }
        });
        return result;
    }
}
