package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-24   19:14
 */
@Mapper
public interface OrderStatisticsMapper {
    void insertorderStatistics(OrderStatistics orderStatistics);

    List<OrderStatistics> selectOrderStatisticsBetweenCreateTime(OrderStatisticsDto orderStatisticsDto);
}
