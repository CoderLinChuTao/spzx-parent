package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * 包名：com.atguigu.spzx.model.entity.system
 *
 * @author lct
 * 日期: 2023-10-16   11:33
 */
@Data
public class SysRole extends BaseEntity {
    private String roleName;
    private String roleCode;
    private String description;
}
