package com.iotcloud.common.openfeign.hanlder;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.iotcloud.common.core.domain.UserDto;
import com.iotcloud.common.core.util.Result;
import org.springframework.web.bind.annotation.RequestParam;

public class UserHandler {


    //若本次访问被限流或服务降级，则调用blockHandler指定的接口。
    //返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException
    public static Result<UserDto> getUserByNameHandlerException(@RequestParam("username") String username, BlockException  blockException) {
        System.out.println("getUserByNameHandlerException用户自定义测试失败");
        return Result.ok(505,null,"handlerException用户自定义测试失败");
    }



    public static UserDto currentUserHandlerException(BlockException  blockException) {
        System.out.println("currentUserHandlerException用户自定义测试失败");
        UserDto userDto = new UserDto();
        userDto.setUsername("currentUserHandlerException用户自定义测试失败");
        return userDto;
    }

}
