package com.atguigu.spzx.model.vo.common;

import lombok.Data;

/**
 * 包名：com.atguigu.spzx.model.vo.common
 *
 * @author lct
 * 日期: 2023-10-12   11:27
 */
@Data
public class Result <T>{
    private Integer code;
    private T data;
    private String message;

    // 私有化构造
    private Result() {}

    public static <T> Result<T> build(Integer code,String message,T data){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }


    public static <T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> result = new Result<>();
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(T o) {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(o);
        return result;
    }

    public static <T> Result<T> error(String errorMessage) {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.ERROR.getCode());
        result.setMessage(errorMessage);
        return result;
    }


}
