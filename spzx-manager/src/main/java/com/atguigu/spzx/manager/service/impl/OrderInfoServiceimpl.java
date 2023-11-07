package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.manager.service.OrderInfoService;
import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import com.atguigu.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-24   20:02
 */
@Service
public class OrderInfoServiceimpl implements OrderInfoService {

    @Autowired
    OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        List<OrderStatistics> orderStatistics = orderStatisticsMapper.selectOrderStatisticsBetweenCreateTime(orderStatisticsDto);
        //遍历获取每个orderStatistic的orderDate，totalAmount，再封装到vo中
        List<String> dateList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        for (OrderStatistics orderStatistic : orderStatistics) {
            Date orderDate = orderStatistic.getOrderDate();
            dateList.add(orderDate.toString());
            BigDecimal totalAmount = orderStatistic.getTotalAmount();
            amountList.add(totalAmount);
        }
        OrderStatisticsVo  orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
}
