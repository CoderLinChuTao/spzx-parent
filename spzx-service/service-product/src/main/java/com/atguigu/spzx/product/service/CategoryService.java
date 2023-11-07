package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.product.service
 *
 * @author lct
 * 日期: 2023-10-30   16:37
 */
@Mapper
public interface CategoryService {
    List<Category> findOneCategory(Long parentId);

    List<Category> findCategoryTree();
}
