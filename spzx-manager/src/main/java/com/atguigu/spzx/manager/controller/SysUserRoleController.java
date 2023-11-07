package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.service.SysUserRoleService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
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
@RequestMapping("/admin/system/sysRoleUser")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysUserRoleController {
    @Autowired
    SysUserRoleService sysUserRoleService;

    @PostMapping ("doAssign")
    public Result findByPage(@RequestBody AssginRoleDto assginRoleDto){
        sysUserRoleService.doAssign(assginRoleDto );
        return Result.ok(null);
    }
}
