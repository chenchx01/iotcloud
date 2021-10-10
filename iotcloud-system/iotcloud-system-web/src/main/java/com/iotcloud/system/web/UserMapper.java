package com.iotcloud.system.web;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotcloud.common.core.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<UserDto> {

    @Select("select * from iot_user where username = #{username}")
    UserDto getUserByName(String username);
}
