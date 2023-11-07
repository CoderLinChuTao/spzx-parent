package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.user.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名：com.atguigu.spzx.user.service
 *
 * @author lct
 * 日期: 2023-10-31   20:01
 */
@Mapper
public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    boolean checkPhoneExist(String phone);

    String login(UserLoginDto userLoginDto, String ip);

    UserInfoVo getCurrentUserInfo(String token);

    UserInfo checkToken(String token);
}
