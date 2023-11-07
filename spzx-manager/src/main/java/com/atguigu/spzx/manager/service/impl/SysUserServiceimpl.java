package com.atguigu.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.util.Assert;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.FileUploadService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-12   16:42
 */
@Service
public class SysUserServiceimpl implements com.atguigu.spzx.manager.service.SysUserService {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    FileUploadService fileUploadService;
    @Override
    public LoginVo login(LoginDto loginDto) {

        //检验登录参数
        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();
        Assert.isTrue(!StringUtils.isEmpty(userName)&&!StringUtils.isEmpty(password),"用户不存在");

        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key
        String captchaCache = redisTemplate.opsForValue().get(codeKey);
        Assert.isTrue(!StringUtils.isEmpty(captchaCache)&&captchaCache.equals(captcha),"验证码错误" );

        //查询数据库用户对象
        SysUser sysUser=sysUserMapper.selectByName(userName);

        Assert.notNull(sysUser,"用户不存在");

        //验证密码是否正确
        String passwordFromDb = sysUser.getPassword();
        Assert.isTrue(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(passwordFromDb),"密码错误");
        //登录成功，生成token
        LoginVo loginVo=new LoginVo();
        String token = UUID.randomUUID().toString().replace("-", "");
        //redis存储一份,命名：object：id：field，比如user：22：password
        redisTemplate.opsForValue().set("user:login:" + token , JSON.toJSONString(sysUser));
        loginVo.setToken(token);
        loginVo.setRefresh_token("non");

        //登录成功后，删除验证码
        redisTemplate.delete(codeKey);

        return loginVo;
    }

    @Override
    public SysUser getUserinfo(String token) {
        String useInfoJsonStr = redisTemplate.opsForValue().get("user:login:" + token);
        Assert.notNull(useInfoJsonStr,"用户未登录或者不存在");
        SysUser sysUser = JSON.parseObject(useInfoJsonStr, SysUser.class);
        return sysUser;
    }

    @Override
    public ValidateCodeVo generateValidateCode() {
        CircleCaptcha circleCaptcha= CaptchaUtil.createCircleCaptcha(150,48,4, 20);

        String code =circleCaptcha.getCode();
        String imageBase64=circleCaptcha.getImageBase64();

        String key ="user:login:validatecode:"+System.currentTimeMillis();

        //将key和code存储在后端redis
        redisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);
        //key和图片给前端
        ValidateCodeVo validateCodeVo=new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64,"+imageBase64);

        return validateCodeVo;
    }

    @Override
    public PageInfo<SysUser> findByPage(Integer page, Integer limit, SysUserDto sysUserDto) {
        PageHelper.startPage(page,limit);
        List<SysUser> sysUserList = sysUserMapper.selectUserByPage(sysUserDto);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(sysUserList);
        return  sysUserPageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        String avatar = sysUser.getAvatar();
        if(StringUtils.isEmpty(avatar)){
            sysUser.setAvatar("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        }
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        sysUser.setStatus(1);
        sysUserMapper.insertSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));

        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }
}
