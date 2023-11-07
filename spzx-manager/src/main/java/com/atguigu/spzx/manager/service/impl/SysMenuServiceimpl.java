package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.dto.system.SysMenu;
import com.atguigu.spzx.model.entity.system.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysMenuVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-18   20:07
 */
@Service
public class SysMenuServiceimpl implements SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //所有菜单数据非树状
        List<SysMenu> allNodes = sysMenuMapper.selectAllNodes();

        List<SysMenu> treeNodes = MenuHelper.buildTree(allNodes);
        return treeNodes;
    }

    @Override
    public void saveSysUser(SysMenu sysMenu) {
        //有parentId没有主键
        sysMenuMapper.insertSysMenu(sysMenu);
    }

    @Override
    public void updateSysUser(SysMenu sysMenu) {
        sysMenuMapper.updateSysMenuById(sysMenu);
    }




    @Override
    public List<SysMenuVo> menus() {
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
        // 数据库
        List<SysMenu> sysMenus = sysMenuMapper.selectMenusByUserId(userId);
        List<SysMenu> sysMenusTree = MenuHelper.buildTree(sysMenus);

        // 转化为vo
        List<SysMenuVo> sysMenuVos = new ArrayList<>();
        sysMenuVos = buildMenus(sysMenusTree);

        return sysMenuVos;
    }

    @Override
    public void deleteById(Long id) {
        int count = sysMenuMapper.selectByParentId(id);  // 先查询是否存在子菜单，如果存在不允许进行删除
        if (count > 0) {
            throw new RuntimeException("请先删除子菜单");
        }
        sysMenuMapper.updateIsDeleteById(id);
    }

    private List<SysMenuVo> buildMenus(List<SysMenu> sysMenusTree) {
        List<SysMenuVo> sysMenuVos = new ArrayList<>();
        for (SysMenu sysMenu : sysMenusTree) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            // 第二层
            List<SysMenu> children = sysMenu.getChildren();
            if(!CollectionUtils.isEmpty(children)){
                sysMenuVo.setChildren(buildMenus(children));// 这里需要递归
            }
            sysMenuVos.add(sysMenuVo);
        }
        return sysMenuVos;
    }
}
