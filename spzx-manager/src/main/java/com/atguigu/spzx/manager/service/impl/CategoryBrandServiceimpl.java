package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.CategoryBrandMapper;
import com.atguigu.spzx.manager.service.CategoryBrandService;
import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-21   09:45
 */
@Service
public class CategoryBrandServiceimpl implements CategoryBrandService {
    @Autowired
    CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page,limit);
        List<CategoryBrand> categoryBrands = categoryBrandMapper.selectByPage(categoryBrandDto);
        PageInfo<CategoryBrand> pageInfo = new PageInfo<>(categoryBrands);
        return pageInfo;

    }

    @Override
    public void saveCategoryBrand(CategoryBrand categoryBrand) {
        categoryBrandMapper.insertCategoryBrand(categoryBrand);
    }

    @Override
    public void updateBrandById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateCategoryBrand(categoryBrand);
    }

    @Override
    public void removeBrandById(Long categoryBrandId) {
        categoryBrandMapper.updateCategoryBrandById(categoryBrandId);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        List<Brand> brands = categoryBrandMapper.selectBrandByCategoryId(categoryId);
        return brands;
    }
}
