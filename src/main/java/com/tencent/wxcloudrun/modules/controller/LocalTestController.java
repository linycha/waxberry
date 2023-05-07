package com.tencent.wxcloudrun.modules.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.WxPayKit;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.common.utils.SnowFlakeUtil;
import com.tencent.wxcloudrun.modules.dto.OrderInfoDTO;
import com.tencent.wxcloudrun.modules.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("local")
@Slf4j
public class LocalTestController {
    @Autowired
    private WxPayService wxService;

    @Resource
    private OrderService orderService;


    @ApiOperation("统一下单")
    @PostMapping("/unifiedOrder")
    public Res<Map<String, String>> unifiedOrder(@RequestBody OrderInfoDTO dto) throws Exception
    {
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody("测试");
        wxPayUnifiedOrderRequest.setOutTradeNo(dto.getOrderNo());
        wxPayUnifiedOrderRequest.setTotalFee(1);
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setNotifyUrl("payCallBack");
        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
        wxPayUnifiedOrderRequest.setOpenid("oo4Tw5CF5-aGqH-I1xfswnunanm4");
/*        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        request.setDescription("测试");
        request.setOutTradeNo("测试");
        request.setNotifyUrl("https://www.weixin.qq.com/wxpay/pay.php");*/

        WxPayUnifiedOrderResult result = wxService.unifiedOrder(wxPayUnifiedOrderRequest);
        System.out.println(result);
        Map<String, String> map = WxPayKit.miniAppPrepayIdCreateSign(result.getAppid(),result.getPrepayId(),
                wxService.getConfig().getMchKey(), SignType.MD5);
        System.out.println(map.toString());
        return Res.success(map);
    }

    /**
     * 接收支付返回的消息
     * @param
     */
    @RequestMapping(value="/parseOrderNotifyResult",method = RequestMethod.POST)
    @ApiOperation(value="接收支付返回的消息")
    @ResponseBody
    public String parseOrderNotifyResult(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("============支付回调开始");
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxService.parseOrderNotifyResult(xmlResult);
            System.out.println(result.toString());
            // 结果正确
            String orderId = result.getOutTradeNo();
            String tradeNo = result.getTransactionId();
            //String totalFee = WxPayBaseResult.feeToYuan(result.getTotalFee());
            //自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            System.out.println("============支付回调结束");
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}" + e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }


    @ApiOperation("查询订单")
    @PostMapping("/queryOrder")
    public Res<WxPayOrderQueryResult> queryOrder(String outTradeNo) throws Exception
    {
        WxPayOrderQueryResult wxPayOrderQueryResult = wxService.queryOrder(null, outTradeNo);
        return Res.success(wxPayOrderQueryResult);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(SnowFlakeUtil.getOrderNo());
        }
    }
}
