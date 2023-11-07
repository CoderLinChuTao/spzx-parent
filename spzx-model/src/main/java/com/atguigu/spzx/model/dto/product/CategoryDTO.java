package com.atguigu.spzx.model.dto.product;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 包名：com.atguigu.spzx.model.dto.product
 *
 * @author lct
 * 日期: 2023-10-20   08:39
 * Dto 数据传输所使用的对象
 */
@Data
public class CategoryDTO {
    @ExcelProperty("id")
    private Long id;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("图片url")
    private String imageUrl;
    @ExcelProperty("上级id")
    private Long parentId;
    @ExcelProperty("状态")
    private Integer status;
    @ExcelProperty("排序")
    private Integer orderNum;

}
