package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.product.CategoryDTO;
import com.atguigu.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-19   17:03
 */
@Mapper
public interface CategoryMapper {
    List<Category> selectByParentId(Long parentId);

    Long hasChildren(Long id);

    void insertCategoryBatch(List<CategoryDTO> cart);

    List<Category> selectAll();
}
