package com.atguigu.spzx.manager;

import com.atguigu.spzx.manager.properties.UserAuthProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager
 *
 * @author lct
 * 日期: 2023-10-14   12:34
 */
@SpringBootTest
public class Test1 {

    @Autowired
    UserAuthProperties userAuthProperties;
    @Test
    public void test(){
        System.out.println("容器和jupiter一起启动");
        List<String> noAuthUrls = userAuthProperties.getNoAuthUrls();
        System.out.println("noAuthUrls = " + noAuthUrls);
    }
}
