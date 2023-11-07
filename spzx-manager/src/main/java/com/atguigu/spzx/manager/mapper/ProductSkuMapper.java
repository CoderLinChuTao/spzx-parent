package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-24   11:13
 */
public interface ProductSkuMapper {
    void insertProductSku(ProductSku productSku);

    List<ProductSku> selectByProductId(Long productId);

    void updateById(ProductSku productSku);

    void deleteByProductId(Long id);
}
