package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/admin/system/sysRoleMenu")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysRoleMenuController {
    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @GetMapping ("findSysRoleMenuByRoleId/{roleId}")
    public Result<Map<String , Object>> findByPage(@PathVariable Long roleId){
        Map<String, Object> map = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.ok(map);
    }

    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto){
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.ok(null);
    }

}
