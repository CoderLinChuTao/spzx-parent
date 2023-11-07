package com.atguigu.spzx.model.vo.system;

import lombok.Data;

import java.util.List;

// com.atguigu.spzx.model.vo.system
@Data
public class SysMenuVo {
    
    private String title;
    private String name;
    private List<SysMenuVo> children;

}