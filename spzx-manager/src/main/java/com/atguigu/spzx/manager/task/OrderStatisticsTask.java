package com.atguigu.spzx.manager.task;

import com.atguigu.spzx.manager.mapper.OrderInfoMapper;
import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 包名：com.atguigu.spzx.manager.task
 *
 * @author lct
 * 日期: 2023-10-24   18:30
 */
//@Component
public class OrderStatisticsTask {

    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    OrderStatisticsMapper orderStatisticsMapper;
    @Scheduled(cron = "0/5 * * * * ?")
    public void a(){
        LocalDate localDate = LocalDate.of(2023,7,10);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String format = localDate.format(dateTimeFormatter);
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(format);
//        System.out.println("测试 = " + new Date());

        //将结果写入订单统计表
        orderStatisticsMapper.insertorderStatistics(orderStatistics);
    }
}
