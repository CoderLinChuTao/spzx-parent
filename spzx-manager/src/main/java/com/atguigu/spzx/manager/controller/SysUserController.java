package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 包名：com.atguigu.spzx.manager.controller
 *
 * @author lct
 * 日期: 2023-10-12   16:26
 */
@RestController
@RequestMapping("/admin/system/sysUser")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @GetMapping ("findByPage/{page}/{limit}")
    public Result<PageInfo<SysUser>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, SysUserDto sysUserDto){
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(page,limit,sysUserDto );
        return Result.ok(pageInfo);
    }

    @PostMapping("saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser){
        sysUserService.saveSysUser(sysUser);
        return Result.ok(null);
    }

    @PutMapping ("updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser){
        sysUserService.updateSysUser(sysUser);
        return Result.ok(null);
    }

    @DeleteMapping ("deleteById/{userId}")
    public Result deleteById(@PathVariable Long userId){
        sysUserService.deleteById(userId);
        return Result.ok(null);
    }
}
