package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMapper;
import com.atguigu.spzx.manager.mapper.SysUserRoleMapper;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-16   11:10
 */
@Service
public class SysRoleServiceimpl implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Override
    public PageInfo<SysRole> findByPage(Integer page, Integer limit, SysRoleDto sysRoleDto) {
        PageHelper.startPage(page,limit);
        List<SysRole> sysRoles =  sysRoleMapper.selectByPage(sysRoleDto.getRoleName());
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoles);
        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.insertSysRole(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteById(Long id) {
        sysRoleMapper.updateIsDeleteById(id);
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {

        SysUser sysUser = AuthContextUtil.get(); // 当前登录用户id

        Map<String, Object> map = new HashMap<>();
        List<SysRole>  sysRoles= sysRoleMapper.selectAllRoles();

        List<Long> sysUserRoles = sysUserRoleMapper.selectsysUserRoles(userId);

        map.put( "allRolesList",sysRoles);
        map.put( "sysUserRoles",sysUserRoles);
        return map;
    }
}
