package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名：com.atguigu.spzx.order.mapper
 *
 * @author lct
 * 日期: 2023-11-03   19:50
 */
@Mapper
public interface OrderLogMapper {
    void insertOrderLog(OrderLog orderLog);
}
