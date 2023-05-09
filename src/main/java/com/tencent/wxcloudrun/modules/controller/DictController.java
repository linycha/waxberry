package com.tencent.wxcloudrun.modules.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Dict;
import com.tencent.wxcloudrun.modules.service.DictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (dict)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("dict")
public class DictController {
    /**
     * 服务对象
     */
    @Resource
    private DictService dictService;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<Dict>> selectList(QueryDTO dto) {
        return Res.success(dictService.selectPage(dto));
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody Dict entity) {

        boolean result = dictService.saveOrUpdate(entity);
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
        if (StrUtil.isBlank(ids)) {
            return Res.errorMsg("id不能为空！");
        }
        List<Dict> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e ->
                list.add(Dict.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = dictService.updateBatchById(list);
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }
}
