package com.atguigu.spzx.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.spzx.feign.user.UserFeignClient;
import com.atguigu.spzx.model.entity.user.UserInfo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * 包名：com.atguigu.spzx.cloud.gateway.filter
 *
 * @author lct
 * 日期: 2023-10-27   19:51
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Lazy
    @Autowired
    UserFeignClient userFeignClient;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        System.out.println("全局过滤器");
//        System.out.println("全局鉴权");

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        HttpHeaders headers = request.getHeaders();
        RequestPath path = request.getPath();//资源路径
        URI uri = request.getURI();// 全部路径=http://域名:端口号/资源路径
        String hostName = request.getRemoteAddress().getHostName();
        int port = request.getRemoteAddress().getPort();

        // 获取token
        List<String> tokens = headers.get("token");
        if(null!=tokens){
            String token = tokens.get(0);
            // 远程调用user，校验token
            // 非阻塞线程
            if(StringUtils.isNotBlank(token)){
                CompletableFuture<UserInfo> userInfoCompletableFuture = CompletableFuture.supplyAsync(new Supplier<UserInfo>() {
                    @Override
                    public UserInfo get() {
                        UserInfo userInfo = userFeignClient.checkToken(token);
                        return userInfo;
                    }
                });
                UserInfo userInfo = userInfoCompletableFuture.get();
                if(null!=userInfo){
                    System.out.println(userInfo);
                    // 放行，将获取的user信息传入request
                    request.mutate().header("userId",userInfo.getId()+"");
                    request.mutate().header("userInfo", JSON.toJSONString(userInfo, SerializerFeature.BrowserCompatible));
                    exchange.mutate().request(request);
                }

            }

        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;// 优先级最高
    }
}
