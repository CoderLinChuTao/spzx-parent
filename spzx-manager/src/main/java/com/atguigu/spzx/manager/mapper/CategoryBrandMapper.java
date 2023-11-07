package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-21   09:48
 */
@Mapper
public interface CategoryBrandMapper {
    List<CategoryBrand> selectByPage(CategoryBrandDto categoryBrandDto);

    void insertCategoryBrand(CategoryBrand categoryBrand);

    void updateCategoryBrand(CategoryBrand categoryBrand);

    void updateCategoryBrandById(Long categoryBrandId);

    List<Brand> selectBrandByCategoryId(Long categoryId);
}
