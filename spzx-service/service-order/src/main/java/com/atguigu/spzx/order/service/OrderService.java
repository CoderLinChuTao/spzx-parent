package com.atguigu.spzx.order.service;

import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名：com.atguigu.spzx.order.service
 *
 * @author lct
 * 日期: 2023-11-03   16:37
 */
@Mapper
public interface OrderService {
    TradeVo trade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> getOrderListPage(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getOrderByOrderNo(String orderNo);
}
