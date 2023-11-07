package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-19   17:23
 */
public interface CategoryService {
    List<Category> findByParentId(Long parentId);

    void importData(MultipartFile file);

    void exportData(HttpServletResponse response) throws IOException;
}
