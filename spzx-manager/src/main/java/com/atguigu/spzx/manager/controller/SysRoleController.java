package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 包名：com.atguigu.spzx.manager.controller
 *
 * @author lct
 * 日期: 2023-10-12   16:26
 */
@RestController
@RequestMapping("/admin/system/sysRole")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @GetMapping ("findPage/{page}/{limit}")
    public Result<PageInfo<SysRole>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, SysRoleDto sysRoleDto){
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(page,limit,sysRoleDto);
        return Result.ok(pageInfo);
    }

    @GetMapping ("findAllRoles/{userId}")
    public Result<Map<String,Object>> findAllRoles(@PathVariable Long userId){
        Map<String,Object> map = sysRoleService.findAllRoles(userId);
        return Result.ok(map);
    }

    @PostMapping("saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return Result.ok(null);
    }
    @PutMapping ("updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return Result.ok(null);
    }
    @DeleteMapping ("deleteById/{id}")
    public Result deleteById(@PathVariable Long id){
        sysRoleService.deleteById(id);
        return Result.ok(null);
    }

}
