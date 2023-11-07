package com.atguigu.spzx.model.entity.system;

/**
 * 包名：com.atguigu.spzx.model.entity.system
 *
 * @author lct
 * 日期: 2023-10-13   20:24
 */
public class AuthContextUtil {
    public static ThreadLocal<SysUser> sysUserThreadLocal =new ThreadLocal<>();

    public static SysUser get(){
        SysUser sysUser = sysUserThreadLocal.get();
        return sysUser;
    }

    public static void set(SysUser sysUser){
        sysUserThreadLocal.set(sysUser);
    }

}
