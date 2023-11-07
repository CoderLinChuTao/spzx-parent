package com.atguigu.spzx.common.log.service;

import com.atguigu.spzx.model.entity.system.SysOperLog;

/**
 * 包名：com.atguigu.spzx.common.log.service
 *
 * @author lct
 * 日期: 2023-10-25   16:27
 */
public interface SysOperLogService {
    void saveSysOperLog(SysOperLog sysOperLog);
}
