package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductUnitMapper;
import com.atguigu.spzx.manager.service.ProductUnitService;
import com.atguigu.spzx.model.entity.product.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-23   20:46
 */
@Service
public class ProductUnitServiceimpl implements ProductUnitService {
    @Autowired
    ProductUnitMapper productUnitMapper;
    @Override
    public List<ProductUnit> findAll() {
        List<ProductUnit> productUnits = productUnitMapper.selectAll();
        return productUnits;
    }
}
