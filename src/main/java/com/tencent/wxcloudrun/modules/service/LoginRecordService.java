package com.tencent.wxcloudrun.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Dict;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.modules.entity.LoginRecord;
import com.tencent.wxcloudrun.modules.dao.LoginRecordMapper;
@Service
public class LoginRecordService extends ServiceImpl<LoginRecordMapper, LoginRecord> {
    public IPage<LoginRecord> selectPage(QueryDTO dto) {
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());

        return baseMapper.selectPage(page, dto);
    }
}
