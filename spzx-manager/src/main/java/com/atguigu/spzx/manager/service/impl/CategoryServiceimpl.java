package com.atguigu.spzx.manager.service.impl;


import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.manager.listner.CategoryDTOExcelListener;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.dto.product.CategoryDTO;
import com.atguigu.spzx.model.entity.product.Category;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-19   17:02
 */
@Service
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> findByParentId(Long parentId) {
        List<Category> categories = categoryMapper.selectByParentId(parentId);//查询多级数据
        for (Category category : categories) {
            //获取id通过当前id是否有hasChildren，如果count>0,设置为true
            Long id =category.getId();
            Long count = categoryMapper.hasChildren(id);
            if (count > 0) {
                category.setHasChildren(true);
            }
        }
        return categories;
    }
    @SneakyThrows
    @Override
    public void importData(MultipartFile file) {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, CategoryDTO.class,new CategoryDTOExcelListener(categoryMapper))
        .sheet("分类数据").doRead();
    }

    @SneakyThrows
    @Override
    public void exportData(HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        String sheetName = "分类列表";
        String encode = URLEncoder.encode(sheetName, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + encode + ".xlsx");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        ServletOutputStream outputStream = response.getOutputStream();
        List<CategoryDTO> categoryExcelDTOs = new ArrayList<>();
        List<Category> categories = categoryMapper.selectAll();
        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(category,categoryDTO);
            categoryExcelDTOs.add(categoryDTO);
        }
        EasyExcel.write(outputStream,CategoryDTO.class).sheet("分类数据").doWrite(categoryExcelDTOs);

    }
}
