package com.atguigu.spzx.feign.user;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 包名：com.atguigu.spzx.feign.user
 *
 * @author lct
 * 日期: 2023-11-01   18:28
 */

@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/api/user/userInfo/checkToken/{token}")
    public UserInfo checkToken(@PathVariable String token);

    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id);
}
