package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductSpecMapper;
import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-23   11:19
 */
@Service
public class ProductSpecServiceimpl implements ProductSpecService {
    @Autowired
    private ProductSpecMapper productSpecMapper ;

    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page , limit) ;
        List<ProductSpec> productSpecList = productSpecMapper.findByPage() ;
        return new PageInfo<>(productSpecList);
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.insertProductSpec(productSpec);
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateProductSpec(productSpec);
    }

    @Override
    public void DeleteProductSpecById(Long productSpecId) {
        productSpecMapper.updateProductSpecById(productSpecId);
    }

    @Override
    public List<ProductSpec> findAll() {
        List<ProductSpec> productSpecList = productSpecMapper.selectAll();
        return productSpecList;
    }

}
