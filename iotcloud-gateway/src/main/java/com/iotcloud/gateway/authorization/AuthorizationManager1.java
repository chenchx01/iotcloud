package com.iotcloud.gateway.authorization;

import cn.hutool.core.convert.Convert;
import com.iotcloud.common.core.constant.AuthConstant;
import com.iotcloud.common.core.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 * Created by macro on 2020/6/19.
 */
//@Component
public class AuthorizationManager1 implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        //从Redis中获取当前路径可访问角色列表, /a/b-----[admin,test1,test2]
        URI uri = authorizationContext.getExchange().getRequest().getURI();
      //  HttpMethod method = authorizationContext.getExchange().getRequest().getMethod();
        //System.out.println("method:"+method);
        Object obj = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP1, uri.getPath());
        List<String> authorities = Convert.toList(String.class,obj);
//        if(ObjectUtils.isEmpty(authorities)){
//            authorities.add("ADMIN");
//        }
        if(request.getMethod()== HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }
        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}