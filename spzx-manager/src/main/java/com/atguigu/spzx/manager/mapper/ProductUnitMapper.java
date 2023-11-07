package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-23   20:47
 */@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> selectAll();
}
