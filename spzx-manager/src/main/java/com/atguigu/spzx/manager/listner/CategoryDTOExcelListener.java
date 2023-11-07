package com.atguigu.spzx.manager.listner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.model.dto.product.CategoryDTO;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.listner
 *
 * @author lct
 * 日期: 2023-10-20   08:33
 */
@NoArgsConstructor
public class CategoryDTOExcelListener extends AnalysisEventListener<CategoryDTO> {



    List<CategoryDTO> cart = new ArrayList<>();

    CategoryMapper categoryMapper;

    public CategoryDTOExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void invoke(CategoryDTO categoryDTO, AnalysisContext analysisContext) {
        cart.add(categoryDTO);
        if(cart.size()>=10){
            // System.out.println("写入数据库："+cart);
            categoryMapper.insertCategoryBatch(cart);
            cart.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("收尾："+cart);
        categoryMapper.insertCategoryBatch(cart);

    }
}
