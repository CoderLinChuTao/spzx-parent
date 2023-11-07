package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Cacheable(value = "findOneCategory",key = "#parentId")
    @Override
    public List<Category> findOneCategory(Long parentId) {
        List<Category> categories = categoryMapper.selectOneCategory();
        return categories;
    }


    @Cacheable(value = "findCategoryTree",key = "'all'")
    @Override
    public List<Category> findCategoryTree() {

        List<Category> categories = categoryMapper.selectAllCategory();
        List<Category> categories1 = CategoryHelper.buildTree(categories);
        redisTemplate.opsForValue().set("test1","test1");

//        // 一级分类
//        List<Category> categories1 = new ArrayList<>();
//        for (Category category : categories) {
//            if (category.getParentId().longValue() == 0L) {
//                categories1.add(category);
//            }
//        }
//
//        // 为一级分类添加二级分类，二级分类添加三级分类
//        for (Category category1 : categories1) {
//            List<Category> categories2 = categories.stream().filter(categorie -> {
//                return categorie.getParentId().longValue() == category1.getId().longValue();
//            }).collect(Collectors.toList());
//            for (Category category2 : categories2) {
//                List<Category> categories3 = categories.stream().filter(categorie -> {
//                    return categorie.getParentId().longValue() == category2.getId().longValue();
//                }).collect(Collectors.toList());
//                category2.setChildren(categories3);
//            }
//            category1.setChildren(categories2);
//        }

        return categories1;
    }
}
