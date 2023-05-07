package com.tencent.wxcloudrun.modules.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.WxPayKit;
import com.tencent.wxcloudrun.common.utils.SnowFlakeUtil;
import com.tencent.wxcloudrun.common.utils.UserUtils;
import com.tencent.wxcloudrun.modules.dao.OrderMapper;
import com.tencent.wxcloudrun.modules.dto.OrderInfoDTO;
import com.tencent.wxcloudrun.modules.dto.OrderStatusCountDTO;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Resource
    private OrderItemService itemService;

    @Resource
    private CartService cartService;

    @Autowired
    private WxPayService wxService;

    public IPage<OrderInfoDTO> selectPage(QueryDTO dto) {
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());
        IPage<OrderInfoDTO> infoDTOIPage = baseMapper.selectPage(page, dto);
        infoDTOIPage.getRecords().forEach( e->{
            StringBuilder str = new StringBuilder();
            AtomicInteger totalNum = new AtomicInteger();
            e.getItemList().forEach(t ->{
                totalNum.addAndGet(t.getNum());
                str.append(t.getProductName()).append(" ").append(t.getSellPrice()).append("*").append(t.getNum());
            });
            str.append(" ");
            e.setProductStr(str.toString());
            e.setTotalNum(totalNum.get());
        });
        return infoDTOIPage;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> createOrder(OrderInfoDTO dto) throws WxPayException {
        String orderNo = SnowFlakeUtil.getId();
        dto.setUserId(UserUtils.getUserId());
        dto.setOrderNo(orderNo);
        List<Integer> cartIdList = new ArrayList<>();
        dto.getItemList().forEach(e -> {
            cartIdList.add(e.getId());
            e.setId(null);
            e.setOrderNo(orderNo);
            e.setTotalPrice(dto.getTotalPrice());
        });
        cartService.removeByIds(cartIdList);
        Order order = new Order();
        BeanUtil.copyProperties(dto, order);
        // 保存收货地址
        order.setShippingAddress(dto.getShipping().getProvince()+dto.getShipping().getCity()
        +dto.getShipping().getCounty()+dto.getShipping().getAddress());
        order.setShippingName(dto.getShipping().getName()+" "+dto.getShipping().getTel());
        this.save(order);
        itemService.saveBatch(dto.getItemList());
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody("林家杨梅订单");
        wxPayUnifiedOrderRequest.setOutTradeNo(dto.getOrderNo());
        wxPayUnifiedOrderRequest.setTotalFee(yuanToPenny(dto.getPayMoney()));
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setNotifyUrl("payCallBack");
        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
        wxPayUnifiedOrderRequest.setOpenid(UserUtils.getOpenId());
/*        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        request.setDescription("测试");
        request.setOutTradeNo("测试");
        request.setNotifyUrl("https://www.weixin.qq.com/wxpay/pay.php");*/

        WxPayUnifiedOrderResult result = wxService.unifiedOrder(wxPayUnifiedOrderRequest);
        System.out.println(result);
        Map<String, String> map = WxPayKit.miniAppPrepayIdCreateSign(result.getAppid(),result.getPrepayId(),
                wxService.getConfig().getMchKey(), SignType.MD5);
        map.put("orderNo", dto.getOrderNo());
        return map;

    }

    public Map<String, String> payOrder(OrderInfoDTO dto) throws WxPayException {
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody("林家杨梅订单");
        wxPayUnifiedOrderRequest.setOutTradeNo(dto.getOrderNo());
        wxPayUnifiedOrderRequest.setTotalFee(yuanToPenny(dto.getPayMoney()));
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setNotifyUrl("payCallBack");
        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
        wxPayUnifiedOrderRequest.setOpenid(UserUtils.getOpenId());
/*        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        request.setDescription("测试");
        request.setOutTradeNo("测试");
        request.setNotifyUrl("https://www.weixin.qq.com/wxpay/pay.php");*/

        WxPayUnifiedOrderResult result = wxService.unifiedOrder(wxPayUnifiedOrderRequest);
        System.out.println(result);
        Map<String, String> map = WxPayKit.miniAppPrepayIdCreateSign(result.getAppid(),result.getPrepayId(),
                wxService.getConfig().getMchKey(), SignType.MD5);
        map.put("orderNo", dto.getOrderNo());
        return map;
    }


    public int updateStatusByOrderNo(Order order){
        return baseMapper.updateStatusByOrderNo(order);
    }

    public OrderStatusCountDTO selectStatusCount(String userId){
        return baseMapper.selectStatusCount(userId);
    }

    public static int yuanToPenny(BigDecimal price){
        if(price == null){
            return 0;
        }
        //元乘以100，并四舍五入，并取整
        String str = price.multiply(new BigDecimal(100)).toString();
        int index = str.indexOf(".");
        if(index > 0){
            return Integer.parseInt(str.substring(0,str.indexOf(".")));
        }else{
            return Integer.parseInt(str.substring(0,str.length()));
        }

    }

    public static void main(String[] args) {
        System.out.println(yuanToPenny(new BigDecimal("12.23")));
        System.out.println(yuanToPenny(new BigDecimal("34.00")));
        System.out.println(yuanToPenny(new BigDecimal("54")));
        System.out.println(yuanToPenny(new BigDecimal("0.02")));
    }
}
