package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.order.mapper
 *
 * @author lct
 * 日期: 2023-11-03   19:46
 */
@Mapper
public interface OrderItemMapper {
    void insertOrderItem(OrderItem orderItem);

    List<OrderItem> selectByOrderId(Long orderInfoId);
}
