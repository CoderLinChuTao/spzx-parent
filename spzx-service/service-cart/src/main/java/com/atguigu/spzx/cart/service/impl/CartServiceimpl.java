package com.atguigu.spzx.cart.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.model.entity.product.CartInfo;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 包名：com.atguigu.spzx.cart.service.impl
 *
 * @author lct
 * 日期: 2023-11-02   14:15
 */
@Service
public class CartServiceimpl implements CartService {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    ProductFeignClient productFeignClient;
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        // 调用feign远程根据skuId查询商品数据
        Result<ProductSku> productSkuResult = productFeignClient.getBySkuId(skuId);
        UserInfo userInfo = AuthContextUtil.get();

        // 将商品数据封装成购物车数据
        CartInfo cartInfo = new CartInfo();//防止空指针
        String cartInfoJSON = (String)redisTemplate.opsForHash().get("user:cart:" + userInfo.getId(), skuId + "");

        if (StringUtils.isNotBlank(cartInfoJSON)) {
            //修改
            cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum()+skuNum);
        }else {
            //添加
            cartInfo = new CartInfo();
            ProductSku productSku = productSkuResult.getData();
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setIsChecked(1);
            cartInfo.setSkuId(skuId);
            cartInfo.setSkuNum(skuNum);
            cartInfo.setUserId(userInfo.getId());
        }
        //存入redis
        redisTemplate.opsForHash().put("user:cart:" + userInfo.getId(),skuId + "",JSON.toJSONString(cartInfo));

    }

    @Override
    public List<CartInfo> cartList() {
        ArrayList<CartInfo> carts = new ArrayList<>();
        UserInfo userInfo = AuthContextUtil.get();
        List<Object> cartValues = redisTemplate.opsForHash().values("user:cart:" + userInfo.getId());
        for (Object cartValue : cartValues) {
            CartInfo cartInfo = JSON.parseObject(cartValue.toString(), CartInfo.class);
            carts.add(cartInfo);
        }
        return carts;
    }

    @Override
    public void deleteCart(Long skuId) {
        UserInfo userInfo = AuthContextUtil.get();
        redisTemplate.opsForHash().delete("user:cart:" + userInfo.getId(),skuId+"");
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        UserInfo userInfo = AuthContextUtil.get();
        CartInfo cartInfo = new CartInfo();
        String cartInfoJSON = (String)redisTemplate.opsForHash().get("user:cart:" + userInfo.getId(), skuId + "");
        if (StringUtils.isNotBlank(cartInfoJSON)) {
            cartInfo= JSON.parseObject(cartInfoJSON,CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put("user:cart:" + userInfo.getId(),skuId+"",JSON.toJSONString(cartInfo));
        }
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        UserInfo userInfo = AuthContextUtil.get();
        List<CartInfo> cartInfos = cartList();
        for (CartInfo cartInfo : cartInfos) {
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put("user:cart:" + userInfo.getId(),cartInfo.getSkuId()+"",JSON.toJSONString(cartInfo));
        }

    }

    @Override
    public List<CartInfo> getAllCkecked() {
        List<CartInfo> cartInfosChecked = new ArrayList<>();
        UserInfo userInfo = AuthContextUtil.get();
        List<Object> cartValues = redisTemplate.opsForHash().values("user:cart:" + userInfo.getId());

        for (Object cartValue : cartValues) {
            CartInfo cartInfo = JSON.parseObject(cartValue.toString(), CartInfo.class);
            if (cartInfo.getIsChecked()==1) {
                cartInfosChecked.add(cartInfo);
            }
        }
        return cartInfosChecked;
    }
}
