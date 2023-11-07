package com.atguigu.spzx.model.entity.user;

import com.atguigu.spzx.model.entity.system.SysUser;

public class AuthContextUtil {

    public static ThreadLocal<UserInfo> sysUserThreadLocal = new ThreadLocal<>();


    public static UserInfo get(){
        UserInfo userInfo = sysUserThreadLocal.get();
        return userInfo;
    }

    public static void set(UserInfo userInfo){
        sysUserThreadLocal.set(userInfo);
    }

}
