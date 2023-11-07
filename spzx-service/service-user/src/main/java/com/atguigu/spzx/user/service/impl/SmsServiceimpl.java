package com.atguigu.spzx.user.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.spzx.common.util.Assert;
import com.atguigu.spzx.common.util.HttpUtils;
import com.atguigu.spzx.user.service.SmsService;
import com.atguigu.spzx.user.service.UserInfoService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 包名：com.atguigu.spzx.user.service.impl
 *
 * @author lct
 * 日期: 2023-10-31   19:03
 */
@Service
public class SmsServiceimpl implements SmsService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    UserInfoService userInfoService;

    @Override
    public void sendValidateCode(String phone) {
        // 校验手机号
        boolean b = userInfoService.checkPhoneExist(phone);
        Assert.isTrue(!b,"手机号已经被注册");

        //生成短信验证码
        String validateCode = RandomStringUtils.randomNumeric(4);

        // 发送短信
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "4f3e491bd75441d9902f12f530a3b0c3";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "**code**:"+validateCode+",**minute**:5");

//smsSignId（短信前缀）和templateId（短信模板），可登录国阳云控制台自助申请。参考文档：http://help.guoyangyun.com/Problem/Qm.html

        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            String data = EntityUtils.toString(response.getEntity());
            HashMap<String, String> resultMap = JSONObject.parseObject(data, HashMap.class);
            // 存入redis
            // 发送成功后，讲验证码存入redis
            String success = resultMap.get("msg");
            if(StringUtils.isNotBlank(success)&&success.equals("成功")){
                redisTemplate.opsForValue().set("phone:code:" + phone,validateCode,5, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
