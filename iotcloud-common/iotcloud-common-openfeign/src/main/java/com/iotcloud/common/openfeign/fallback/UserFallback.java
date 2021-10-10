package com.iotcloud.common.openfeign.fallback;


import com.iotcloud.common.core.domain.UserDto;
import com.iotcloud.common.core.util.Result;

public class UserFallback {

    //若本接口出现未知异常，则调用fallback指定的接口。参数和返回值需要保持一致
    public static Result<UserDto> getUserByNameFallbackException(String username) {
        System.out.println("getUserByNameFallbackException用户自定义测试失败");
        return Result.ok(505,null,"用户调用失败");
    }

    public static UserDto currentUserFallbackException() {
        System.out.println("currentUserHandlerException用户自定义测试失败");
        UserDto userDto = new UserDto();
        userDto.setUsername("currentUserHandlerException用户自定义测试失败");
        return userDto;
    }

}

