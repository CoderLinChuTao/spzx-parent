package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysUserDto;
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
public interface SysUserMapper {
    SysUser selectByName(String userName);

    List<SysUser> selectUserByPage(SysUserDto sysUserDto);

    void insertSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);


    void deleteById(Long userId);
}
