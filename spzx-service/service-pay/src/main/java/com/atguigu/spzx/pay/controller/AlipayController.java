package com.atguigu.spzx.pay.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.pay.properties.AlipayProperties;
import com.atguigu.spzx.pay.service.AlipayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order/alipay")
public class AlipayController {
    @Autowired
    private AlipayService alipayService;


    @Autowired
    AlipayProperties alipayProperties;


    @SneakyThrows
    @PostMapping("callback/notify")
    public String notifyAlipay(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {

        boolean b = AlipaySignature.rsaCheckV1(paramMap, alipayProperties.getAlipayPublicKey(), alipayProperties.charset,AlipayProperties.sign_type);
        System.out.println("验签结果："+b);
        if(b){
            // 支付宝回调业务
            System.out.println("。。。。支付宝回调接口。。。。");
            alipayService.notifyAlipay(paramMap);
            return "success";
        }else {
            return "fail";
        }

    }

    @GetMapping("submitAlipay/{orderNo}")
    @ResponseBody
    public Result<String> submitAlipay(@PathVariable String orderNo) {
        String form = alipayService.submitAlipay(orderNo);
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }
}