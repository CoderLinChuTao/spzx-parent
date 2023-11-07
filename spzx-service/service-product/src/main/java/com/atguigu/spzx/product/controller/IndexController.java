package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;

import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.entity.user.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;

import com.atguigu.spzx.model.vo.h5.IndexVo;


import com.atguigu.spzx.product.service.IndexService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value="/api/product/index")
//@CrossOrigin
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {

   @Autowired
   IndexService indexService;

   @GetMapping
   public Result<IndexVo> findData(HttpServletRequest request){
      String userId = request.getHeader("userId");
      UserInfo userInfo = AuthContextUtil.get();
      IndexVo indexVo = indexService.findData();
      return Result.ok(indexVo);
   }

}