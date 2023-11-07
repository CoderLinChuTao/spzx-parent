package com.atgui.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * 包名：com.atgui.spzx.common.exception
 *
 * @author lct
 * 日期: 2023-10-13   11:41
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result commonException(Exception e){
        //使用日志查看错误
        log.error(new Date() +e.getMessage());
        return Result.error("通用异常："+e.getMessage());
    }

        @ExceptionHandler(BadSqlGrammarException.class)//针对不同类型的异常定义不同异常
        public Result badSqlGrammarException(BadSqlGrammarException e){
            log.error("sql错误原因："+e.getSql()+"sql根本原因："+e.getMessage());
            return Result.error("sql:"+e.getMessage());
        }
}
