package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.product.mapper
 *
 * @author lct
 * 日期: 2023-10-30   16:50
 */
@Mapper
public interface CategoryMapper {
    List<Category> selectOneCategory();

    List<Category> selectAllCategory();
}
