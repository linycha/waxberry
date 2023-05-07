package com.tencent.wxcloudrun.modules.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.tencent.wxcloudrun.common.Const;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.common.utils.UserUtils;
import com.tencent.wxcloudrun.modules.dto.OrderInfoDTO;
import com.tencent.wxcloudrun.modules.dto.OrderStatusCountDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Order;
import com.tencent.wxcloudrun.modules.service.CartService;
import com.tencent.wxcloudrun.modules.service.OrderItemService;
import com.tencent.wxcloudrun.modules.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (order)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    @Resource
    private OrderItemService itemService;

    @Resource
    private CartService cartService;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<OrderInfoDTO>> selectList(QueryDTO dto) {
        if(ObjectUtil.equals(UserUtils.getRole(),"user")){
            dto.setUserId(UserUtils.getUserId());
        }
        return Res.success(orderService.selectPage(dto));
    }

    @ApiOperation("统一下单")
    @PostMapping("/createOrder")
    public Res<Map<String, String>> createOrder(@RequestBody OrderInfoDTO dto) throws WxPayException {
        if(CollUtil.isEmpty(dto.getItemList())){
            return Res.errorMsg("不能为空");
        }
        return Res.success(orderService.createOrder(dto));
    }

    @ApiOperation("未支付订单提交支付")
    @PostMapping("/payOrder")
    public Res<Map<String, String>> payOrder(@RequestBody OrderInfoDTO dto) throws WxPayException {
        if(StrUtil.isBlank(dto.getOrderNo())){
            return Res.errorMsg("orderNo不能为空");
        }
        return Res.success(orderService.payOrder(dto));
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody Order entity) {

        boolean result = orderService.saveOrUpdate(entity);
        if (result) {
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

    @PostMapping("surePay")
    public Res<String> surePay(@RequestBody Order entity) {
        if(StrUtil.isBlank(entity.getOrderNo())){
            return Res.errorMsg("参数错误");
        }
        entity.setStatus(Const.ORDER_STATUS_PAY);

        int result = orderService.updateStatusByOrderNo(entity);
        if (result > 0) {
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

    @PostMapping("cancel")
    public Res<String> cancel(@RequestBody Order entity) {
        if(StrUtil.isBlank(entity.getOrderNo())){
            return Res.errorMsg("参数错误");
        }
        entity.setStatus(Const.ORDER_STATUS_CANCEL);

        int result = orderService.updateStatusByOrderNo(entity);
        if (result > 0) {
            return Res.successMsg("取消成功");
        }
        return Res.errorMsg("取消失败");
    }

    @GetMapping("getOrderStatusCount")
    public Res<OrderStatusCountDTO> getOrderStatusCount(QueryDTO dto) {
        return Res.success(orderService.selectStatusCount(UserUtils.getUserId()));
    }


    /**
     * 删除操作
     */
    @DeleteMapping("delete")
    public Res<String> delete(String ids) {
        if (StrUtil.isBlank(ids)) {
            return Res.errorMsg("id不能为空！");
        }
        List<Order> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e ->
                list.add(Order.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = orderService.updateBatchById(list);
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }
}
