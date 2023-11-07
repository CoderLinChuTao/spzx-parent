package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-20   13:47
 */
public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void saveBrand(Brand brand);

    void updateBrandById(Brand brand);

    void deleteBrandById(Long brandId);

    List<Brand> findAll();
}
