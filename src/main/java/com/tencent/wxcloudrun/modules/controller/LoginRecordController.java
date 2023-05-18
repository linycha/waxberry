package com.tencent.wxcloudrun.modules.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.LoginRecord;
import com.tencent.wxcloudrun.modules.service.LoginRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (login_record)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("login_record")
public class LoginRecordController {
    /**
     * 服务对象
     */
    @Resource
    private LoginRecordService loginRecordService;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<LoginRecord>> selectList(QueryDTO dto) {
        return Res.success(loginRecordService.selectPage(dto));
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody LoginRecord entity) {

        boolean result = loginRecordService.saveOrUpdate(entity);
        if (result) {
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

    /**
     * 删除操作
     */
    @DeleteMapping("delete")
    public Res<String> delete(String ids) {
        return Res.successMsg("删除成功！");
    }
}
