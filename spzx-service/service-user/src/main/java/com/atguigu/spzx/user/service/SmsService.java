package com.atguigu.spzx.user.service;

/**
 * 包名：com.atguigu.spzx.user.service
 *
 * @author lct
 * 日期: 2023-10-31   19:03
 */
public interface SmsService {
    void sendValidateCode(String phone);
}
