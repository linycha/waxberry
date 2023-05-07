package com.tencent.wxcloudrun.modules.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Product;
import com.tencent.wxcloudrun.modules.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (product)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<Product>> selectList(QueryDTO dto) {
        return Res.success(productService.selectPage(dto));
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody Product entity) {

        boolean result = productService.saveOrUpdate(entity);
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
        List<Product> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e ->
                list.add(Product.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = productService.updateBatchById(list);
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }
}
