package com.atgui.spzx.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.user.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果token不为空，那么此时验证token的合法性
        String userInfoJSON = request.getHeader("userInfo");
        if(StringUtils.isNotBlank(userInfoJSON)){
            UserInfo userInfo = JSON.parseObject(userInfoJSON, UserInfo.class);
            if(null!=userInfo){
                AuthContextUtil.set(userInfo);
            }
        }
        return true ;
    }

}