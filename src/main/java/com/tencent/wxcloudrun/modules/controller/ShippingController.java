package com.tencent.wxcloudrun.modules.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.common.utils.UserUtils;
import com.tencent.wxcloudrun.modules.dao.ShippingMapper;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Shipping;
import com.tencent.wxcloudrun.modules.service.ShippingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (shipping)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("shipping")
public class ShippingController {
    /**
     * 服务对象
     */
    @Resource
    private ShippingService shippingService;
    @Resource
    private ShippingMapper shippingMapper;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<Shipping>> selectList(QueryDTO dto) {
        dto.setUserId(UserUtils.getUserId());
        return Res.success(shippingService.selectPage(dto));
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody Shipping entity) {
        if(entity.getId() ==  null){
            entity.setUserId(UserUtils.getUserId());
        }
        if(StrUtil.equals(entity.getIsDefault(), "1")){
            shippingMapper.updateIsDefault(entity);
        }
        boolean result = shippingService.saveOrUpdate(entity);
        if (result) {
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

    /**
     * 删除操作
     */
    @GetMapping("delete")
    public Res<String> delete(String ids) {
        if (StrUtil.isBlank(ids)) {
            return Res.errorMsg("id不能为空！");
        }
        List<Shipping> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e ->
                list.add(Shipping.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = shippingService.updateBatchById(list);
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }
}
