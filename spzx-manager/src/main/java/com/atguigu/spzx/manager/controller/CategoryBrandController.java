package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.log.anno.Log;
import com.atguigu.spzx.manager.service.CategoryBrandService;
import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService ;

    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList =   categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList , ResultCodeEnum.SUCCESS) ;
    }

    @Log(title = "分类商标", businessType = 0,isSaveRequestData = true)
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer page,
                                                      @PathVariable Integer limit,
                                                      CategoryBrandDto CategoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, CategoryBrandDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    @Transactional
    @Log(title = "分类商标", businessType = 1,isSaveRequestData = true)
    @PostMapping("save")
    public Result SaveCategoryBrand(@RequestBody CategoryBrand categoryBrand){
        categoryBrandService.saveCategoryBrand(categoryBrand);
//        int a=1/0;
        return Result.ok(null);
    }

    @PutMapping ("updateById")
    public Result updateBrandById(@RequestBody CategoryBrand categoryBrand){
        categoryBrandService.updateBrandById(categoryBrand);
        return Result.ok(null);
    }
    @DeleteMapping ("remove/{categoryBrandId}")
    public Result removeBrandById(@PathVariable Long categoryBrandId){
        categoryBrandService.removeBrandById(categoryBrandId);
        return Result.ok(null);
    }

}