package com.iotcloud.system.analysis.control;

import com.alibaba.fastjson.JSONObject;
import com.iotcloud.common.core.domain.UserDto;
import com.iotcloud.common.openfeign.web.UserServiceFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestControl {

    @Resource
    UserServiceFeign testService;

    @GetMapping("/currentUser")
    public UserDto currentUser() {
        return testService.currentUser();
    }

    @PostMapping("/t")
    public JSONObject test() {
        JSONObject result = new JSONObject();
        result.put("code",200);
        result.put("msg","success");
        return result;
    }
}
