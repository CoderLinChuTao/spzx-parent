package com.atguigu.spzx.order.controller;

import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.atguigu.spzx.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单管理")
@RestController
@RequestMapping(value="/api/order/orderInfo")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoController {
   
   @Autowired
   private OrderService orderService;

   @GetMapping("getOrderByOrderNo/{orderNo}")
   public OrderInfo getOrderByOrderNo(@PathVariable String orderNo){
      OrderInfo orderInfo = orderService.getOrderByOrderNo(orderNo);
      return orderInfo;
   }

   @Operation(summary = "确认下单")
   @GetMapping("auth/trade")
   public Result<TradeVo> trade(){
      TradeVo tradeVo = orderService.trade();
      return Result.ok(tradeVo);
   }
   @PostMapping("auth/submitOrder")
   public Result<Long> submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
      Long orderId = orderService.submitOrder(orderInfoDto);
      return Result.build(orderId, ResultCodeEnum.SUCCESS);
   }

   @GetMapping("auth/{orderId}")
   public Result<OrderInfo> getOrderInfo(@PathVariable Long orderId) {
      OrderInfo orderInfo = orderService.getOrderInfo(orderId);
      return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
   }

   @GetMapping("auth/buy/{skuId}")
   public Result<TradeVo> buy(@PathVariable Long skuId) {
      TradeVo tradeVo = orderService.buy(skuId);
      return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
   }

   @GetMapping("/auth/{page}/{limit}")
   public Result<PageInfo<OrderInfo>> getOrderListPage(@PathVariable Integer page, @PathVariable Integer limit, Integer orderStatus) {
      PageInfo<OrderInfo> orderInfoPageInfo = orderService.getOrderListPage(page,limit,orderStatus);
      return Result.build(orderInfoPageInfo, ResultCodeEnum.SUCCESS);
   }

}