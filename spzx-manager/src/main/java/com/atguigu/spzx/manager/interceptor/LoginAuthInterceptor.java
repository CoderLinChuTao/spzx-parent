package com.atguigu.spzx.manager.interceptor;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.util.Assert;
import com.atguigu.spzx.model.entity.system.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 包名：com.atguigu.spzx.manager.interceptor
 *
 * @author lct
 * 日期: 2023-10-13   20:32
 */
@Slf4j
@Component
public class LoginAuthInterceptor  implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
     HttpServletResponse response,
     Object handler) throws Exception {


        // 获取请求方式
        String method = request.getMethod();
        if("OPTIONS".equals(method)) {      // 如果是跨域预检请求，直接放行
            return true ;
        }


//        Assert.notNull(token,"用户未登录或者不存在");
//        Assert.notNull(useInfoJsonStr,"用户未登录或者不存在");
//        if (null==sysUser){
//            responseNoLoginInfo(response);
//        }
        String requestURI = request.getRequestURI();
        String token = request.getHeader( "token");
        if (StringUtils.isEmpty(token)){
            log.error("token为空"+requestURI);
        }
        String useInfoJsonStr = redisTemplate.opsForValue().get("user:login:" + token);
        if (StringUtils.isEmpty(useInfoJsonStr)){
            log.error("useInfoJsonStr"+requestURI);
        }
        SysUser sysUser = JSON.parseObject(useInfoJsonStr, SysUser.class);
        if (StringUtils.isEmpty(sysUser)){
            log.error("sysUser"+requestURI);
        }
        AuthContextUtil.set(sysUser);
        return true;
    }
    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
