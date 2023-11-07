package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysUserRoleMapper;
import com.atguigu.spzx.manager.service.SysUserRoleService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 * 描述：在给用户分配角色的场景中，为了确保数据的准确性和一致性，通常会先删除中间表中该用户已有的角色关联关系，然后再重新插入新的角色关联关系。
 *
 * 这样做的目的是避免重复数据或冗余数据的存在。如果不先删除中间表对应关系，而直接进行插入操作，可能会导致重复的角色关联关系数据被插入到中间表中，从而引发数据不一致的问题。
 *
 * 因此，在执行 doAssign 方法时，首先通过调用 sysUserRoleMapper.deleteRoleUserId(userId) 来删除 userId 对应的旧的角色关联关系，然后再通过调用 sysUserRoleMapper.insertRoleByUserId(assginRoleDto) 将新的角色关联关系插入到中间表中。
 *
 * 这样的操作能够确保中间表中只包含最新的角色关联关系数据，保持数据的一致性和正确性。
 * @author lct
 * 日期: 2023-10-18   18:14
 */
@Service
public class SysUserRoleServiceimpl implements SysUserRoleService{

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {

        //删除（中间表）对应关系
        Long userId = assginRoleDto.getUserId();
        sysUserRoleMapper.deleteRoleUserId(userId);

        //重新保存
        sysUserRoleMapper.insertRoleByUserId(assginRoleDto);
    }
}
