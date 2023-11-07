package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.product.mapper
 *
 * @author lct
 * 日期: 2023-10-31   11:36
 */
public interface BrandMapper {
    List<Brand> findAll();
}
