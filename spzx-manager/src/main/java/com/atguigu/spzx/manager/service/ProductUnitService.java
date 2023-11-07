package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-23   20:43
 */
@Mapper
public interface ProductUnitService {
    List<ProductUnit> findAll();
}
