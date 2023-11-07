package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import org.springframework.stereotype.Service;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-18   18:13
 */
public interface SysUserRoleService {
    void doAssign(AssginRoleDto assginRoleDto);
}
