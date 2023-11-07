package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.vo.h5.IndexVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名：com.atguigu.spzx.product.service
 *
 * @author lct
 * 日期: 2023-11-01   21:07
 */
@Mapper
public interface IndexService {
    IndexVo findData();
}
