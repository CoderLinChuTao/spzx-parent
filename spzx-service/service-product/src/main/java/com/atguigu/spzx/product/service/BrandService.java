package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.product.service
 *
 * @author lct
 * 日期: 2023-10-31   11:35
 */
public interface BrandService {
    List<Brand> findAll();
}
