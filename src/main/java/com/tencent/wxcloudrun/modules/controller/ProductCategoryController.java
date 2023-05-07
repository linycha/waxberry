package com.tencent.wxcloudrun.modules.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.ProductCategory;
import com.tencent.wxcloudrun.modules.service.ProductCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (product_category)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("product_category")
public class ProductCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private ProductCategoryService productCategoryService;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<ProductCategory>> selectList(QueryDTO dto) {
        return Res.success(productCategoryService.selectPage(dto));
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody ProductCategory entity) {

        boolean result = productCategoryService.saveOrUpdate(entity);
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
        List<ProductCategory> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e ->
                list.add(ProductCategory.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = productCategoryService.updateBatchById(list);
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }
}
