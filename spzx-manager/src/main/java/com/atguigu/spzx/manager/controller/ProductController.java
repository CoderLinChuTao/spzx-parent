package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getById/{productId}")
    public Result<Product> getById(@PathVariable Long productId) {
        Product product = productService.getById(productId);
        return Result.build(product , ResultCodeEnum.SUCCESS);
    }

    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<Product>> findByParentId(@PathVariable Integer page, @PathVariable Integer limit , ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page,limit,productDto);
        return Result.ok(pageInfo) ;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@Parameter(name = "id", description = "商品id", required = true) @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping("/updateAuditStatus/{productId}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long productId,@PathVariable Integer auditStatus) {
        productService.updateAuditStatus(productId,auditStatus);
        return Result.ok(null) ;
    }

    @GetMapping("/updateStatus/{productId}/{status}")
    public Result updateStatus(@PathVariable Long productId,@PathVariable Integer status) {
        productService.updateStatus(productId,status);
        return Result.ok(null) ;
    }
}