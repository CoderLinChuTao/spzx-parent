package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.BrandMapper;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-20   13:48
 */
@Service
public class BrandServiceimpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {

        PageHelper.startPage(page,limit);
        List<Brand> brandList = brandMapper.selectByPage();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return pageInfo;
    }

    @Override
    public void saveBrand(Brand brand) {
        brandMapper.insertBrand(brand);
    }

    @Override
    public void updateBrandById(Brand brand) {
        brandMapper.updateBrand(brand);
    }

    @Override
    public void deleteBrandById(Long brandId) {
        brandMapper.updateBrandById(brandId);
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brandList = brandMapper.selectAll();
        return brandList;
    }
}
