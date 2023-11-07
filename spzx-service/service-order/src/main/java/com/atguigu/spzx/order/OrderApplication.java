package com.atguigu.spzx.order;

import com.atgui.spzx.common.anno.EnableUserTokenFeignInterceptor;
import com.atgui.spzx.common.anno.EnableUserWebMvcConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.atguigu.spzx.feign.cart","com.atguigu.spzx.feign.product","com.atguigu.spzx.feign.user"
})
@MapperScan(basePackages = "com.atguigu.spzx.order.mapper")
@EnableUserWebMvcConfiguration
@EnableUserTokenFeignInterceptor
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }

}