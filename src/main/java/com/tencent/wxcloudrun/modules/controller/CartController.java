package com.tencent.wxcloudrun.modules.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.common.utils.UserUtils;
import com.tencent.wxcloudrun.modules.dao.CartMapper;
import com.tencent.wxcloudrun.modules.dto.CartDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Cart;
import com.tencent.wxcloudrun.modules.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * (cart)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("cart")
public class CartController {
    /**
     * 服务对象
     */
    @Resource
    private CartService cartService;
    @Resource
    private CartMapper cartMapper;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<CartDTO>> selectList(QueryDTO dto) {
        dto.setUserId(UserUtils.getUserId());
        return Res.success(cartService.selectPage(dto));
    }

    /**
     * 购物车数量加1
     */
    @PostMapping("add")
    public Res<String> add(@RequestBody Cart entity) {
        if(entity.getId() == null) {
            entity.setUserId(UserUtils.getUserId());
        }
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", UserUtils.getUserId());
        queryWrapper.eq("product_id", entity.getProductId());
        Cart cart = cartService.getOne(queryWrapper);
        if(cart != null) {
            if(cart.getNum() >= 99) {
                return Res.errorMsg("购物车数量超出上限");
            }
            cartMapper.addCartNum(cart);
        }else{
            entity.setNum(1);
            cartService.save(entity);
        }
        return Res.successMsg("新增或修改成功");
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("minus")
    public Res<String> minus(@RequestBody Cart entity) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", UserUtils.getUserId());
        queryWrapper.eq("product_id", entity.getProductId());
        Cart cart = cartService.getOne(queryWrapper);
        if(cart != null) {
            cartMapper.minusCartNum(cart);
        }
        return Res.successMsg("成功");
    }

    @PostMapping("updateNum")
    public Res<String> updateNum(@RequestBody Cart entity) {
        if(entity.getNum() == null){
            return Res.errorMsg("数量不能为空");
        }
        if(entity.getNum() == 0){
            cartService.removeById(entity.getId());
        }
        cartMapper.updateNum(entity);
        return Res.successMsg("成功");
    }
    /**
     * 删除操作
     */
    @GetMapping("delete")
    public Res<String> delete(String ids) {
        if (StrUtil.isBlank(ids)) {
            return Res.errorMsg("id不能为空！");
        }
        boolean result = cartService.removeByIds(Arrays.asList(ids.split(",")));
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }
}
