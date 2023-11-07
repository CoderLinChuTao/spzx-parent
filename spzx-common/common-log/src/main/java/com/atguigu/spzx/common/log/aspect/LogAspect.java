package com.atguigu.spzx.common.log.aspect;


import com.atguigu.spzx.common.log.anno.Log;
import com.atguigu.spzx.common.log.service.SysOperLogService;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    SysOperLogService sysOperLogService;

    @SneakyThrows
    @Around(value = "@annotation(sysLog)")
    public Object a(ProceedingJoinPoint joinPoint, Log sysLog) {// Log是通知所绑定的注解，用来切入到连接点

        SysOperLog sysOperLog = new SysOperLog();

        // 1 log.info("执行方法之前。。。。");，记录操作参数
        beforeHandleLog(sysLog , joinPoint , sysOperLog) ;

        // 2 执行被代理啊方法
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            // 3 log.info("执行方法之后。。。。");记录操作结果
            afterHandlLog(sysLog , proceed , sysOperLog , 0 , null) ;  // 构建响应结果参数
        } catch (Throwable throwable) {
            afterHandlLog(sysLog , proceed , sysOperLog , 1 , throwable.getMessage()) ;
            throw new Exception(throwable.getMessage());
        }finally {
            // 4 记录操作日志
            sysOperLogService.saveSysOperLog(sysOperLog);
        }
        return proceed;
    }

    /***
     * 方法执行后的参数
     * @param sysLog
     * @param proceed
     * @param sysOperLog
     * @param errorCode
     * @param errorMessage
     */
    private void afterHandlLog(Log sysLog, Object proceed, SysOperLog sysOperLog, int errorCode, String errorMessage) {

        sysOperLog.setStatus(errorCode);
        sysOperLog.setErrorMsg(errorMessage);
        // 封装返回结果
        if(sysLog.isSaveResponseData()&&proceed!=null){
            sysOperLog.setJsonResult(proceed.toString());
        }

    }

    /***
     * 方法执行前日志参数
     * @param sysLog
     * @param joinPoint
     * @param sysOperLog
     */
    private void beforeHandleLog(Log sysLog, ProceedingJoinPoint joinPoint, SysOperLog sysOperLog) {


        // 传入方法默认注解参数
        sysOperLog.setTitle(sysLog.title());
        sysOperLog.setOperName(sysLog.operatorType().getName());// 操作人员姓名
        sysOperLog.setBusinessType(sysLog.businessType());
        sysOperLog.setOperatorType(sysLog.operatorType().getType());// 操作用户角色

        // 方法和方法的请求参数
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        String operParam = "";
        if(args!=null&&args.length>0){
            for (Object arg : args) {
                operParam += " "+ arg;
            }
        }
        sysOperLog.setMethod(methodSignature.getName());
        sysOperLog.setOperParam(operParam);

        // servlet参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String method = request.getMethod();
        StringBuffer requestURL = request.getRequestURL();
        String remoteAddr = request.getRemoteAddr();
        sysOperLog.setRequestMethod(method);
        sysOperLog.setOperUrl(requestURL.toString());
        sysOperLog.setOperIp(remoteAddr);

    }

}
