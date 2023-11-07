package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.log.service.SysOperLogService;
import com.atguigu.spzx.manager.mapper.SysOperLogMapper;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 包名：com.atguigu.spzx.common.log.service.impl
 *
 * @author lct
 * 日期: 2023-10-25   16:28
 */
@Service
public class SysOperLogServiceimpl implements SysOperLogService {
    @Autowired
    SysOperLogMapper sysOperLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
    sysOperLogMapper.insertSysOperLog(sysOperLog);
    }
}
