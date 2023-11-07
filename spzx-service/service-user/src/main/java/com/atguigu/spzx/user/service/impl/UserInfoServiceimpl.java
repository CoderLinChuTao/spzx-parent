package com.atguigu.spzx.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.util.Assert;
import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.user.UserInfoVo;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 包名：com.atguigu.spzx.user.service.impl
 *
 * @author lct
 * 日期: 2023-10-31   20:03
 */
@Service
public class UserInfoServiceimpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        // 校验验证码
        String codeCache = redisTemplate.opsForValue().get("phone:code:" + userRegisterDto.getUsername());
        Assert.isTrue(userRegisterDto.getCode().equals(codeCache),"验证码错误");

        // 注册
        String password = DigestUtils.md5DigestAsHex(userRegisterDto.getPassword().getBytes());

        // 保存
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(userRegisterDto.getUsername());
        userInfo.setNickName(userRegisterDto.getNickName());
        userInfo.setPassword(password);
        userInfo.setUsername(userRegisterDto.getUsername());
        userInfoMapper.insertUserInfo(userInfo);


    }

    @Override
    public boolean checkPhoneExist(String phone) {
        int i = userInfoMapper.selectCountUser(phone);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public String login(UserLoginDto userLoginDto, String ip) {

        // 根据用户名查询用户
        UserInfo userInfo = userInfoMapper.selectByUsername(userLoginDto.getUsername());
        Assert.isTrue(userInfo!=null,"该用户不存在");

        // 验证密码
        String passwordDb = userInfo.getPassword();
        Assert.isTrue(DigestUtils.md5DigestAsHex(userLoginDto.getPassword().getBytes()).equals(passwordDb),"密码错误");

        // 记录ip
        userInfo.setLastLoginTime(new Date());
        userInfo.setLastLoginIp(ip);
        userInfoMapper.updateUserLogin(userInfo);


        // 生成token
        String token = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        String userStr = redisTemplate.opsForValue().get("user:login:" + token);
        Assert.isTrue(!StringUtil.isEmpty(userStr),"用户不存在缓存中");
        UserInfo userInfo = JSON.parseObject(userStr, UserInfo.class);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setNickName(userInfo.getNickName());
        userInfoVo.setAvatar(userInfo.getAvatar());
        return userInfoVo;
    }

    @Override
    public UserInfo checkToken(String token) {
        String userInfoJSON = redisTemplate.opsForValue().get("user:login:"+token);
        if(StringUtils.isEmpty(userInfoJSON)) {
            return null ;
        }else {
            return JSON.parseObject(userInfoJSON , UserInfo.class) ;
        }
    }
}
