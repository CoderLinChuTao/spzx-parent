package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-12   18:39
 */
@Mapper
public interface SysRoleMapper {

    List<SysRole> selectByPage(String roleName);

    void insertSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void updateIsDeleteById(Long id);

    List<SysRole> selectAllRoles();
}
