package com.tencent.wxcloudrun.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.modules.dto.CartDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.modules.dao.DictMapper;
import com.tencent.wxcloudrun.modules.entity.Dict;
@Service
public class DictService extends ServiceImpl<DictMapper, Dict> {
    public IPage<Dict> selectPage(QueryDTO dto) {
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());

        return baseMapper.selectPage(page, dto);
    }
}
