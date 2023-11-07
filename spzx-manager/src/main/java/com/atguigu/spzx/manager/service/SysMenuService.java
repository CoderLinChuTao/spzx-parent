package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.SysMenu;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-18   20:04
 */
@Mapper
public interface SysMenuService {
    List<SysMenu> findNodes();

    void saveSysUser(SysMenu sysMenu);

    void updateSysUser(SysMenu sysMenu);

    List<SysMenuVo> menus();

    void deleteById(Long menuId);
}
