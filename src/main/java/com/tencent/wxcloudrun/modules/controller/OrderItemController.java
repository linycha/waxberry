package com.tencent.wxcloudrun.modules.controller;

import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.modules.entity.OrderItem;
import com.tencent.wxcloudrun.modules.service.OrderItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (order_item)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("order_item")
public class OrderItemController {
    /**
     * 服务对象
     */
    @Resource
    private OrderItemService orderItemService;

    /**
     * 查询列表数据
     */
    /*@GetMapping("list")
    public Res<IPage<OrderItem>> selectList(QueryDTO dto) {
        return Res.success(orderItemService.selectPage(dto));
    }*/

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody OrderItem entity) {

        boolean result = orderItemService.saveOrUpdate(entity);
        if (result) {
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

/*    *//**
     * 删除操作
     *//*
    @DeleteMapping("delete")
    public Res<String> delete(String ids) {
        if (StrUtil.isBlank(ids)) {
            return Res.errorMsg("id不能为空！");
        }
        List<OrderItem> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e ->
                list.add(OrderItem.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = orderItemService.updateBatchById(list);
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }*/
}
