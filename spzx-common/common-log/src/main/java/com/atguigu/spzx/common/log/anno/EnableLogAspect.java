package com.atguigu.spzx.common.log.anno;

import com.atguigu.spzx.common.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 包名：com.atguigu.spzx.common.log.anno
 * 1：让启动器扫描到自定义注解 EnableLogAspect               //aop实现日志的思路
 * 2：使用aspect注解定义切面类
 * 3：使用@around注解定义环绕通知
 * 4：使用@anno注解定义绑定切点注解(@Log),@log可以使用在被aop绑定的任何方法上  @log中可以自定义属性
 * 5： 定义注解属性(自定义)，放在方法上时候，可以将方法的相关信息赋值给@log
 * 6：调用业务层，将sysLog存储到数据
 *
 *
 *
 * @author lct
 * 日期: 2023-10-24   20:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(LogAspect.class)
public @interface EnableLogAspect {
}
