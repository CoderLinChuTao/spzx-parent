package com.atguigu.spzx.manager;

import com.atgui.spzx.common.anno.EnableGlobaleExceptionHandler;
import com.atgui.spzx.common.exception.GlobalExceptionHandler;
import com.atguigu.spzx.common.log.anno.EnableLogAspect;
import com.atguigu.spzx.manager.interceptor.LoginAuthInterceptor;
import com.atguigu.spzx.manager.properties.UserAuthProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 包名：com.atguigu.spzx.manager
 *
 * @author lct
 * 日期: 2023-10-11   17:17
 */
@SpringBootApplication
@MapperScan("com.atguigu.spzx.manager.mapper")
//@Import(GlobalExceptionHandler.class)//在spzx-manager中使用全局异常处理器,方法一
//@EnableGlobaleExceptionHandler//方法二
@EnableScheduling
@EnableLogAspect
@Import({LoginAuthInterceptor.class, UserAuthProperties.class})
public class ManagerApplication {
    public static  void main(String[] args){
        SpringApplication.run(ManagerApplication.class,args);
    }
}
