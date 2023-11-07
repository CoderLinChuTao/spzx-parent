package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.dto.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.controller
 *
 * @author lct
 * 日期: 2023-10-18   20:00
 */
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("findNodes")
    public Result<List<SysMenu>> findNodes(){
        List<SysMenu> sysMenuList = sysMenuService.findNodes();
        return Result.ok(sysMenuList);
    }

    @PostMapping("save")
    public Result saveSysMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.saveSysUser(sysMenu);
        return Result.ok(null);
    }

    @PutMapping ("updateById")
    public Result updateSysMenuById(@RequestBody SysMenu sysMenu){
        sysMenuService.updateSysUser(sysMenu);
        return Result.ok(null);
    }

    @DeleteMapping ("deleteMenuById/{menuId}")
    public Result deleteById(@PathVariable Long menuId){
        sysMenuService.deleteById(menuId);
        return Result.ok(null);
    }
}
