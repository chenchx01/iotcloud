package com.iotcloud.system.web.holder;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.iotcloud.common.core.domain.UserDto;
import com.iotcloud.common.core.util.Result;
import com.iotcloud.system.web.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息
 */
@Component
public class LoginUserHolder {

    @Resource
    UserMapper userMapper;

    public UserDto getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        System.out.println("userStr:"+userStr);
        JSONObject userJsonObject = new JSONObject(userStr);
        UserDto userDTO = new UserDto();
        userDTO.setUsername(userJsonObject.getStr("user_name"));
        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        return userDTO;
    }

    public Result<UserDto> getUserByName(String username){
        if(ObjectUtils.isEmpty(username)){
            return Result.ok(null);
        }
     //   int k =1/0;
        UserDto  userDto = userMapper.getUserByName(username);
        return Result.ok(userDto);
    }

}
