package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.mapper
 *
 * @author lct
 * 日期: 2023-10-19   11:23
 */
@Mapper
public interface SysRoleMenuMapper {
    List<Long> selectRoleMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void insertAssginMenuDto(AssginMenuDto assginMenuDto);
}
