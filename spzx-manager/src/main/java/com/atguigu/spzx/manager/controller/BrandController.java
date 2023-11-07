package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.dto.system.SysMenu;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @GetMapping(value = "/findAll")
    public Result<List<Brand>> findAll() {
        List<Brand> brandList = brandService.findAll();
        return Result.ok(brandList) ;
    }
    @GetMapping(value = "/findAllBrand")
    public Result<List<Brand>> findAllBrand() {
        List<Brand> brandList = brandService.findAll();
        return Result.ok(brandList) ;
    }


    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<Brand>> findByParentId(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page,limit);
        return Result.ok(pageInfo) ;
    }
    @PostMapping("save")
    public Result saveBrand(@RequestBody Brand brand){
        brandService.saveBrand(brand);
        return Result.ok(null);
    }

    @PutMapping ("updateById")
    public Result updateBrandById(@RequestBody Brand brand){
        brandService.updateBrandById(brand);
        return Result.ok(null);
    }
    @DeleteMapping ("{brandId}")
    public Result deleteBrandById(@PathVariable Long brandId){
        brandService.deleteBrandById(brandId);
        return Result.ok(null);
    }

}