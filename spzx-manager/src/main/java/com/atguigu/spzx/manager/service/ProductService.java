package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDto;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-23   18:32
 */
@Mapper
public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void save(Product product);

    Product getById(Long productId);

    void updateById(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long productId, Integer auditStatus);

    void updateStatus(Long productId, Integer status);
}
