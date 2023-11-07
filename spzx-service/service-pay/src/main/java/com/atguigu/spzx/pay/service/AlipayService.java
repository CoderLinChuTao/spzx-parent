package com.atguigu.spzx.pay.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 包名：com.atguigu.spzx.pay.service
 *
 * @author lct
 * 日期: 2023-11-04   16:38
 */

public interface AlipayService {
    String submitAlipay(String orderNo);

    void notifyAlipay(Map<String, String> paramMap);
}
