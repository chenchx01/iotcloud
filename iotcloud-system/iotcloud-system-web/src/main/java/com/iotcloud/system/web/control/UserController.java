package com.iotcloud.system.web.control;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.iotcloud.common.core.domain.UserDto;
import com.iotcloud.common.core.util.Result;
import com.iotcloud.common.openfeign.fallback.UserFallback;
import com.iotcloud.common.openfeign.hanlder.UserHandler;
import com.iotcloud.system.web.holder.LoginUserHolder;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 获取登录用户信息接口
 * Created by macro on 2020/6/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginUserHolder loginUserHolder;

    @Resource
    RestHighLevelClient client;

    @GetMapping("/currentUser")
    @SentinelResource(value = "currentUser", blockHandlerClass = UserHandler.class, blockHandler = "currentUserHandlerException", fallbackClass = UserFallback.class, fallback = "currentUserFallbackException")
    public UserDto currentUser() throws IOException {
        System.out.println(client);
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("iotcloud");
        // 2、客户端执行请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse =
                client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
        return loginUserHolder.getCurrentUser();
    }

    @PostMapping("/getUserByName")
    @SentinelResource(value = "userInfo", blockHandlerClass = UserHandler.class, blockHandler = "getUserByNameHandlerException", fallbackClass = UserFallback.class, fallback = "getUserByNameFallbackException")
    public Result getUserByName(@RequestParam("username") String username) {
        return loginUserHolder.getUserByName(username);
    }



}
