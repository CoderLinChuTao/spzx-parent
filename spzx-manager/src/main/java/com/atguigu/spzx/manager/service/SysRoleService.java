package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-16   11:09
 */
public interface SysRoleService {

    PageInfo<SysRole> findByPage(Integer page, Integer limit, SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long id);

    Map<String, Object> findAllRoles(Long userId);
}
