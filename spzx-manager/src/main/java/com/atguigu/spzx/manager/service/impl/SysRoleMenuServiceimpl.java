package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.dto.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-19   11:11
 */
@Service
public class SysRoleMenuServiceimpl implements SysRoleMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        //查询所有菜单sysMenuList
        List<SysMenu> sysMenus = sysMenuMapper.selectAllNodes();
        List<SysMenu> sysTree = MenuHelper.buildTree(sysMenus);
        //查询当前所有角色菜单roleMenuIds
        List<Long> roleMenuIds = sysRoleMenuMapper.selectRoleMenuByRoleId(roleId);

        map.put("sysMenuList",sysTree);
        map.put("roleMenuIds",roleMenuIds);
        return map;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        //先删除原有的对应关系
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        //插入新的对应关系
        sysRoleMenuMapper.insertAssginMenuDto(assginMenuDto);
    }
}
