package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名：com.atguigu.spzx.user.mapper
 *
 * @author lct
 * 日期: 2023-10-31   20:29
 */
@Mapper
public interface UserInfoMapper {
    void insertUserInfo(UserInfo userInfo);

    int selectCountUser(String phone);

    UserInfo selectByUsername(String username);

    void updateUserLogin(UserInfo userInfo);
}
