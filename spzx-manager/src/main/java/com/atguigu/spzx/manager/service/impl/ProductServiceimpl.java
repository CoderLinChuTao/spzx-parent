package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.ProductMapper;
import com.atguigu.spzx.manager.mapper.ProductSkuMapper;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.entity.product.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-23   18:33
 */
@Service
@SuppressWarnings({"all"})
public class ProductServiceimpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductSkuMapper productSkuMapper;

    @Autowired
    ProductDetailsMapper productDetailsMapper;

    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page,limit);
        List<Product> products = productMapper.selectProductByPage(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    public void save(Product product) {
        // 保存商品spu信息product，生成productId
        productMapper.insertProduct(product);
        Long productId = product.getId();

        // 保存商品sku信息product_sku
        int i = 0;// sku序号
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (ProductSku productSku : productSkuList) {
            i++;
            productSku.setProductId(productId);
            productSku.setSkuName(product.getName()+" "+productSku.getSkuSpec());// 拼接sku名称
            productSku.setSkuCode(productId+"_"+i);// 拼接skucode
            productSkuMapper.insertProductSku(productSku);
        }

        // 保存商品详情海报信息product_details
        String detailsImageUrls = product.getDetailsImageUrls();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(productId);
        productDetails.setImageUrls(detailsImageUrls);
        productDetailsMapper.insertProductDetails(productDetails);
    }

    @Override
    public Product getById(Long productId) {

        // 先查询product对象
        Product product = productMapper.selectById(productId);

        // 再根据productId查询sku集合
        List<ProductSku> productSkus = productSkuMapper.selectByProductId(productId);
        product.setProductSkuList(productSkus);

        // 再根据productId查询详情
        ProductDetails productDetails = productDetailsMapper.selectByProductId(productId);
        String imageUrls = productDetails.getImageUrls();
        product.setDetailsImageUrls(imageUrls);

        return product;
    }

    @Override
    public void updateById(Product product) {
        // 修改商品基本数据
        productMapper.updateById(product);

        // 修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        // 修改商品的详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);
    }

    @Override
    public void deleteById(Long id) {
        productMapper.deleteById(id);                   // 根据id删除商品基本数据
        productSkuMapper.deleteByProductId(id);         // 根据商品id删除商品的sku数据
        productDetailsMapper.deleteByProductId(id);     // 根据商品的id删除商品的详情数据
    }

    @Override
    public void updateAuditStatus(Long productId, Integer auditStatus) {
        productMapper.updateAuditStatus(productId,auditStatus);
    }

    @Override
    public void updateStatus(Long productId, Integer status) {
        productMapper.updateStatus(productId,status);
    }
}
