package com.iotcloud.gateway.filter;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.iotcloud.common.core.constant.CacheConstants;
import com.iotcloud.common.core.constant.CommonConstants;
import com.iotcloud.common.core.exception.ValidateCodeException;
import com.iotcloud.common.redis.RedisConfig;
import com.iotcloud.common.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory {

    @Resource
    RedisUtils redis;


    @Override
    public GatewayFilter apply(Object config) {
        return (exchange,chain)->{
            ServerHttpRequest request = exchange.getRequest();
            log.info("请求路径：{}",request.getURI().getPath());
            if(CommonConstants.AUTH_URL.equals(request.getURI().getPath())){
                checkCode(request);
            }
            return chain.filter(exchange);
        };
    }
    private void checkCode(ServerHttpRequest request) {
        String uuid = request.getQueryParams().getFirst(CommonConstants.UUID);
        String code =  request.getQueryParams().getFirst(CommonConstants.CODE);

        if (CharSequenceUtil.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (CharSequenceUtil.isBlank(uuid)) {
            uuid = request.getQueryParams().getFirst("mobile");
        }
        String key = CacheConstants.DEFAULT_CODE_KEY + uuid;
        Object codeObj = redis.get(key);
        redis.del(key);
        if (ObjectUtil.isEmpty(codeObj) || !code.equals(codeObj)) {
            throw new ValidateCodeException("验证码错误");
        }
    }
}
