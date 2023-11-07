package com.atguigu.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.atguigu.spzx.model.dto.productsku.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.product.ProductItemVo;

import com.atguigu.spzx.product.mapper.ProductDetailsMapper;
import com.atguigu.spzx.product.mapper.ProductMapper;
import com.atguigu.spzx.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Override
    public List<ProductSku> findProductSkuBySale() {
        return productSkuMapper.findProductSkuBySale();
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page,limit);
        List<ProductSku> productSkuList = productSkuMapper.selectBypage(productSkuDto);
        PageInfo pageInfo = new PageInfo<>(productSkuList);
        return pageInfo;
    }



    @Override
    public ProductItemVo item(Long skuId) {
        // 查询sku
        ProductSku productSku = productSkuMapper.selectSkuById(skuId);

        // 查询product
        Product product = productMapper.selectProductById(productSku.getProductId());

        // 查询skuList
        List<ProductSku> productSkus = productSkuMapper.selectSkuByProductId(productSku.getProductId());

        // 查询details
        ProductDetails productDetails = productDetailsMapper.selectByProductId(productSku.getProductId());


        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);          //sku
        productItemVo.setProduct(product);             //product
        String[] splitSliderUrls = product.getSliderUrls().split(",");
        List<String> splitUrls = Arrays.asList(splitSliderUrls);
        productItemVo.setSliderUrlList(splitUrls);       //product
        String[] splitImageUrls = productDetails.getImageUrls().split(",");
        List<String> stringImageUrls = Arrays.asList(splitImageUrls);
        productItemVo.setDetailsImageUrlList(stringImageUrls); //details
        String specValue = product.getSpecValue();
        JSONArray objects = JSON.parseArray(specValue);
        productItemVo.setSpecValueList(objects);       //product
        HashMap<String, Object> skuSpecValueMap = new HashMap<>();
        for (ProductSku sku : productSkus) {
            skuSpecValueMap.put(sku.getSkuSpec(), sku.getId());
        }
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);     //sku


        return productItemVo;
    }

    @Override
    public ProductSku getBySkuId(Long skuId) {
        ProductSku productSku = productSkuMapper.selectSkuById(skuId);
        return productSku;
    }
}