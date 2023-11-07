package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-23   18:40
 */
@Mapper
public interface ProductMapper {

    List<Product> selectProductByPage(ProductDto productDto);

    void insertProduct(Product product);

    Product selectById(Long productId);

    void updateById(Product product);


    void deleteById(Long id);

    void updateAuditStatus(Long productId, Integer auditStatus);

    void updateStatus(Long productId, Integer status);
}
