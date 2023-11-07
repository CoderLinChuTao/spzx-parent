package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-21   09:45
 */
public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void saveCategoryBrand(CategoryBrand categoryBrand);

    void updateBrandById(CategoryBrand categoryBrand);

    void removeBrandById(Long categoryBrandId);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
