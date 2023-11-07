package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-23   11:19
 */
@Mapper
public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void DeleteProductSpecById(Long productSpecId);

    List<ProductSpec> findAll();
}
