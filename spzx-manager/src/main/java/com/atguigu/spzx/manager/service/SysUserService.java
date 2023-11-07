package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.github.pagehelper.PageInfo;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-12   16:40
 */
public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserinfo(String token);

    ValidateCodeVo generateValidateCode();

    PageInfo<SysUser> findByPage(Integer page, Integer limit, SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
