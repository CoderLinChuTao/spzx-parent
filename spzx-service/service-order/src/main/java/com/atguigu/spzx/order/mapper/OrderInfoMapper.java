package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.order.mapper
 *
 * @author lct
 * 日期: 2023-11-03   19:50
 */
@Mapper
public interface OrderInfoMapper {
    void insertOrder(OrderInfo orderInfo);

    OrderInfo selectById(Long orderId);

    List<OrderInfo> findUserPage(@Param("userId") Long id, @Param("orderStatus") Integer orderStatus);

    OrderInfo selectByOrderNo(String orderNo);
}
