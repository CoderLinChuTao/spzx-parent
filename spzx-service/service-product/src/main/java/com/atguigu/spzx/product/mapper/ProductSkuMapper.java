package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.dto.productsku.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    List<ProductSku> findProductSkuBySale();

    List<ProductSku> selectBypage(ProductSkuDto productSkuDto);

    ProductSku selectSkuById(Long skuId);

    List<ProductSku> selectSkuByProductId(Long productId);
}