package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-20   13:51
 */
@Mapper
public interface BrandMapper {
    List<Brand> selectByPage();

    void insertBrand(Brand brand);

    void updateBrand(Brand brand);

    void updateBrandById(Long brandId);

    List<Brand> selectAll();
}
