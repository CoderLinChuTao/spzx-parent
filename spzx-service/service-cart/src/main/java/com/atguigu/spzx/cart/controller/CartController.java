package com.atguigu.spzx.cart.controller;

import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.model.entity.product.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "购物车接口")
@RestController
@RequestMapping("api/order/cart")
public class CartController {


    @Autowired
    CartService cartService;

    @GetMapping("/auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable Long skuId,@PathVariable Integer skuNum){
        // 添加购物车业务
        cartService.addToCart(skuId,skuNum);
        return Result.ok(null);
    }

    @GetMapping("/auth/cartList")
    public Result<List<CartInfo>> cartList(){
        // 购物车list业务
        List<CartInfo> cartInfos = cartService.cartList();
        return Result.ok(cartInfos);
    }

    @DeleteMapping("/auth/deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId) {
        // 删除购物车业务
        cartService.deleteCart(skuId);
        return Result.ok(null);
    }

    @GetMapping("auth/checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable Long skuId,@PathVariable Integer isChecked){
        cartService.checkCart(skuId,isChecked);
        return Result.ok(null);
    }


    @GetMapping("/auth/allCheckCart/{isChecked}")
    public Result allCheckCart(@PathVariable(value = "isChecked") Integer isChecked){
        cartService.allCheckCart(isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary="选中的购物车")
    @GetMapping(value = "/auth/getAllCkecked")
    public Result<List<CartInfo>> getAllCkecked() {
        List<CartInfo> cartInfoList = cartService.getAllCkecked() ;
        return Result.build(cartInfoList , ResultCodeEnum.SUCCESS) ;
    }
}