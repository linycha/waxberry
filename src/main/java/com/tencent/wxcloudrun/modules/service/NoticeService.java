package com.tencent.wxcloudrun.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.modules.dao.NoticeMapper;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Notice;
import org.springframework.stereotype.Service;

@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {
    public IPage<Notice> selectPage(QueryDTO dto) {
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());

        return baseMapper.selectPage(page, dto);
    }
}
