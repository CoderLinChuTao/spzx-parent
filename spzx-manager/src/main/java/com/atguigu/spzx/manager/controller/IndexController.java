package com.atguigu.spzx.manager.controller;


import cn.hutool.http.server.HttpServerRequest;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.controller
 *
 * @author lct
 * 日期: 2023-10-12   16:26
 */
@RestController
@RequestMapping("/admin/system/index")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    SysMenuService sysMenuService;
    @GetMapping("menus")
    public Result<List<SysMenuVo>> menus(){
        List<SysMenuVo> sysMenuVos = sysMenuService.menus();
        return Result.ok(sysMenuVos);
    }

    @PostMapping("login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){
        LoginVo loginVo= sysUserService.login(loginDto);
        return Result.ok(loginVo);
    }



    @GetMapping ("userinfo")
    public Result<SysUser> getUserinfo(HttpServletRequest request){
        String token = request.getHeader("token");
        SysUser sysUser = sysUserService.getUserinfo(token);
        SysUser sysUserTest = AuthContextUtil.get();
        return Result.ok(sysUserTest);
    }

    @GetMapping("generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCode = sysUserService.generateValidateCode();
        return Result.ok(validateCode);
    }
}
