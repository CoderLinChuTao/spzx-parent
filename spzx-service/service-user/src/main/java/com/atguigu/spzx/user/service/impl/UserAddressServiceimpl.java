package com.atguigu.spzx.user.service.impl;

import com.atguigu.spzx.model.entity.user.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.user.mapper.UserAddressMapper;
import com.atguigu.spzx.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包名：com.atguigu.spzx.user.service.impl
 *
 * @author lct
 * 日期: 2023-11-03   11:36
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserAddressServiceimpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        UserInfo userInfo = AuthContextUtil.get();
        return userAddressMapper.findByUserId(userInfo.getId());
    }

    @Override
    public UserAddress getUserAddress(Long id) {
        UserAddress userAddress = userAddressMapper.selectUserAddressById(id);
        return userAddress;
    }
}
