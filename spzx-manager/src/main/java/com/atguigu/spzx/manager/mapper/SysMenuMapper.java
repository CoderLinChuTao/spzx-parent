package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-18   20:09
 */
@Mapper
public interface SysMenuMapper {
    List<SysMenu> selectAllNodes();

    void insertSysMenu(SysMenu sysMenu);

    void updateSysMenuById(SysMenu sysMenu);

    List<SysMenu> selectMenusByUserId(Long userId);

    void updateIsDeleteById(Long id);

    int selectByParentId(Long id);
}
