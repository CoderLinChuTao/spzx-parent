package com.atguigu.spzx.order.service.impl;

import com.atguigu.spzx.common.util.Assert;
import com.atguigu.spzx.feign.cart.CartFeignClient;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.feign.user.UserFeignClient;
import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.entity.order.OrderItem;
import com.atguigu.spzx.model.entity.order.OrderLog;
import com.atguigu.spzx.model.entity.product.CartInfo;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.atguigu.spzx.order.mapper.OrderInfoMapper;
import com.atguigu.spzx.order.mapper.OrderItemMapper;
import com.atguigu.spzx.order.mapper.OrderLogMapper;
import com.atguigu.spzx.order.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.order.service.impl
 *
 * @author lct
 * 日期: 2023-11-03   16:38
 */
@Service
public class OrderInfoServiceimpl implements OrderService {
    @Autowired
    CartFeignClient cartFeignClient;

    @Autowired
    OrderItemMapper orderItemMapper;


    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    OrderLogMapper orderLogMapper;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    UserFeignClient userFeignClient;
    @Override
    public TradeVo trade() {
        UserInfo userInfo = AuthContextUtil.get();
        // 调用cart获得被选中的购物车列表、传入userInfo的json字符串
        Result<List<CartInfo>> allCheckeds = cartFeignClient.getAllChecked();
        List<CartInfo> carts = allCheckeds.getData();
        //封装TradeVo
        TradeVo tradeVo = new TradeVo();
        List<OrderItem> orderItems = new ArrayList<>();     
        BigDecimal totalAmount = new BigDecimal("0");       //结算总金额
        for (CartInfo cart : carts) {       //遍历carts获得被选中的购物车
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cart.getSkuId());
            orderItem.setSkuName(cart.getSkuName());
            orderItem.setSkuPrice(cart.getCartPrice());
            orderItem.setSkuNum(cart.getSkuNum());
            orderItem.setThumbImg(cart.getImgUrl());
            orderItems.add(orderItem);
            totalAmount = totalAmount.add(cart.getCartPrice().multiply(new BigDecimal(cart.getSkuNum())));
        }
        tradeVo.setTotalAmount(totalAmount);  //结算商品列表
        tradeVo.setOrderItemList(orderItems); //总金额
        return tradeVo;
    }

    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {

        // 校验订单信息
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        for (OrderItem orderItem : orderItemList) {
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId()).getData();
            Assert.isTrue(productSku.getStockNum()>=orderItem.getSkuNum(),"库存数量不足");
            orderItem.setSkuName(productSku.getSkuName());
        }

        // 提交订单的用户
        UserInfo userInfo = AuthContextUtil.get();

        // 保存订单信息，返回orderId
        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userInfo.getId());
        BigDecimal total = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            total = total.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(total);
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        orderInfo.setOriginalTotalAmount(total);
        orderInfo.setOrderStatus(0);
        String orderNo = String.valueOf(System.currentTimeMillis());
        orderInfo.setOrderNo(orderNo);// 外部订单号
        orderInfo.setNickName(userAddress.getName());
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setRemark(orderInfoDto.getRemark());
        orderInfoMapper.insertOrder(orderInfo);
        Long orderInfoId = orderInfo.getId();

        // 根据返回的orderId保存订单详情信息
        for (OrderItem orderItem : orderItemList) {
            // 前端已经封装商品数据，不需要再封装
            orderItem.setOrderId(orderInfoId);
            orderItemMapper.insertOrderItem(orderItem);
        }

        // 记录订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setNote(orderInfoDto.getRemark());
        orderLog.setOperateUser(userInfo.getUsername());
        orderLog.setOrderId(orderInfoId);
        orderLog.setProcessStatus(orderInfo.getOrderStatus());
        orderLogMapper.insertOrderLog(orderLog);

        // 清空购物车中已经提交订单购物车数据

        return orderInfoId;
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        return orderInfo;
    }

    @Override
    public TradeVo buy(Long skuId) {

        // 结算页的商品详情(只有一件)
        ProductSku product = productFeignClient.getBySkuId(skuId).getData();
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(product.getId());
        orderItem.setSkuName(product.getSkuName());
        orderItem.setSkuPrice(product.getSalePrice());
        orderItem.setSkuNum(1);
        orderItem.setThumbImg(product.getThumbImg());
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        // 结算页对象
        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItems);
        tradeVo.setTotalAmount(product.getSalePrice());
        return tradeVo;
    }

    @Override
    public PageInfo<OrderInfo> getOrderListPage(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page,limit);
        UserInfo userInfo = AuthContextUtil.get();
//        List<OrderInfo> orderInfos = orderInfoMapper.selectByUserId(userInfo.getId(),orderStatus);
//        for (OrderInfo orderInfo : orderInfos) {
//            List<OrderItem> orderItems = new ArrayList<>();
//            orderItems = orderItemMapper.selectByOrderId(orderInfo.getId());
//            orderInfo.setOrderItemList(orderItems);
//        }
        List<OrderInfo> orderInfoList = orderInfoMapper.findUserPage(userInfo.getId(), orderStatus);
        PageInfo<OrderInfo> pageInfo = new PageInfo(orderInfoList);
        return pageInfo;
    }

    @Override
    public OrderInfo getOrderByOrderNo(String orderNo) {
        OrderInfo orderInfo = orderInfoMapper.selectByOrderNo(orderNo);
        Long orderInfoId = orderInfo.getId();
        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderInfoId);
        orderInfo.setOrderItemList(orderItems);
        return orderInfo;
    }
}
