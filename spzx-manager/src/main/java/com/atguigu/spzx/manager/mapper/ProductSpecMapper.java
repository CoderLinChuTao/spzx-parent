package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-23   11:20
 */
@Mapper
public interface ProductSpecMapper {
    List<ProductSpec> findByPage();

    void insertProductSpec(ProductSpec productSpec);

    void updateProductSpec(ProductSpec productSpec);

    void updateProductSpecById(Long productSpecId);

    List<ProductSpec> selectAll();
}
