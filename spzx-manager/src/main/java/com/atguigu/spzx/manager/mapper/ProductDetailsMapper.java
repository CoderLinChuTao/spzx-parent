package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-24   11:13
 */
public interface ProductDetailsMapper {
    void insertProductDetails(ProductDetails productDetails);

    ProductDetails selectByProductId(Long productId);

    void updateById(ProductDetails productDetails);

    void deleteByProductId(Long id);
}
