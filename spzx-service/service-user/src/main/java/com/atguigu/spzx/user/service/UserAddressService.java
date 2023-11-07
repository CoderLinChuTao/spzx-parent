package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.user.service
 *
 * @author lct
 * 日期: 2023-11-03   11:35
 */
@Mapper
public interface UserAddressService {

    List<UserAddress> findUserAddressList();

    UserAddress getUserAddress(Long id);
}
