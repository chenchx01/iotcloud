package com.iotcloud.auth.mapper;

import com.iotcloud.common.core.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDtoMapper {

        @Select("select * from iot_user where username = #{username}")
        UserDto getUserByName(String username);
}
