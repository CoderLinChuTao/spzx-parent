package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名：com.atguigu.spzx.product.mapper
 *
 * @author lct
 * 日期: 2023-10-31   18:20
 */
@Mapper
public interface ProductMapper {
    Product selectProductById(Long productId);
}
