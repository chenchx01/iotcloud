package com.iotcloud.common.openfeign.web;

import com.iotcloud.common.core.domain.UserDto;
import com.iotcloud.common.core.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name="iotcloud-system-web")
@RequestMapping("/user")
public interface UserServiceFeign {

    @GetMapping("/currentUser")
    UserDto currentUser();


    @PostMapping("/getUserByName")
    Result<UserDto> getUserByName(@RequestParam("username") String username);

}
