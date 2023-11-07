package com.atguigu.spzx.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.atguigu.spzx.feign.order.OrderFeignClient;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.entity.pay.PaymentInfo;
import com.atguigu.spzx.pay.properties.AlipayProperties;
import com.atguigu.spzx.pay.service.AlipayService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 包名：com.atguigu.spzx.pay.service.impl
 *
 * @author lct
 * 日期: 2023-11-04   16:39
 */
@Service
public class AlipayServiceimpl implements AlipayService {
    @Autowired
    AlipayProperties alipayProperties;

    @Autowired
    OrderFeignClient orderFeignClient;

    @SneakyThrows
    @Override
    public String submitAlipay(String orderNo) {

        OrderInfo orderInfo = orderFeignClient.getOrderByOrderNo(orderNo);

        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getAlipayUrl(), alipayProperties.getAppId(), alipayProperties.getAppPrivateKey(), alipayProperties.format, alipayProperties.charset, alipayProperties.getAlipayPublicKey(), alipayProperties.sign_type);
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl(alipayProperties.notifyPaymentUrl);
        //同步跳转地址，仅支持http/https
        request.setReturnUrl(alipayProperties.returnPaymentUrl);
        /******必传参数******/
        JSONObject bizContent = new JSONObject();
        //商户订单号，商家自定义，保持唯一性
        bizContent.put("out_trade_no", orderNo);//orderNo
        //支付金额，最小值0.01元
        bizContent.put("total_amount", orderInfo.getTotalAmount());
        //订单标题，不可使用特殊符号
        bizContent.put("subject", orderInfo.getOrderItemList().get(0).getSkuName());

        /******可选参数******/
        //手机网站支付默认传值FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "QUICK_WAP_WAY");// 我们调用的支付宝接口官方名称
        //bizContent.put("time_expire", "2022-08-01 22:00:00");


        request.setBizContent(bizContent.toString());
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

        // 将支付信息保存到数据库
        PaymentInfo paymentInfo = new PaymentInfo();
        System.out.println("提交支付：保存paymentInfo表数据");
        return response.getBody();//返回支付表单
    }

    @Override
    public void notifyAlipay(Map<String, String> paramMap) {

        // 更新支付数据
        System.out.println("支付成功：更新paymentInfo表数据");

        // 更新订单数据
        System.out.println("远程调用订单：orderFeignClient");
        System.out.println("更新orderInfo表数据");

        // 更新商品数据
        System.out.println("远程调用product：productFeignClient");
        System.out.println("更新product表数据:商品的库存和销量");

    }

}
