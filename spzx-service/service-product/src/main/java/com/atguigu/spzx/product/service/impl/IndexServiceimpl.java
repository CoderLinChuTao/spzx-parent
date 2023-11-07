package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.h5.IndexVo;
import com.atguigu.spzx.product.service.CategoryService;
import com.atguigu.spzx.product.service.IndexService;
import com.atguigu.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.product.service.impl
 *
 * @author lct
 * 日期: 2023-11-01   21:07
 */
@Service
public class IndexServiceimpl implements IndexService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Override
    public IndexVo findData() {

        List<Category> categories = categoryService.findOneCategory(0L);
        List<ProductSku> productSkus = productService.findProductSkuBySale();

        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categories);
        indexVo.setProductSkuList(productSkus);
        return indexVo;
    }
}
