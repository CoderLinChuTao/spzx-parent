package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-12   18:39
 */
@Mapper
public interface SysUserRoleMapper {


    List<Long> selectsysUserRoles(Long userId);

    void deleteRoleUserId(Long userId);

    void insertRoleByUserId(AssginRoleDto assginRoleDto);
}
