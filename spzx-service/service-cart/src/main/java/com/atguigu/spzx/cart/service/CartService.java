package com.atguigu.spzx.cart.service;

import com.atguigu.spzx.model.entity.product.CartInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.cart.service
 *
 * @author lct
 * 日期: 2023-11-02   14:14
 */
@Mapper
public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> cartList();

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    List<CartInfo> getAllCkecked();
}
