package com.iotcloud.auth.service;

import cn.hutool.core.collection.CollUtil;
import com.iotcloud.auth.mapper.UserDtoMapper;
import com.iotcloud.common.core.constant.MessageConstant;
import com.iotcloud.common.core.util.Result;
import com.iotcloud.common.openfeign.web.UserServiceFeign;
import com.iotcloud.security.domain.SecurityUser;
import com.iotcloud.common.core.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private List<UserDto> userList;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    UserDtoMapper userDtoMapper;

    @Resource
    UserServiceFeign userServiceFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // UserDto  userDTO= userDtoMapper.getUserByName(username);
        Result<UserDto> userDTOr= userServiceFeign.getUserByName(username);
        System.out.println("userDTOr:"+userDTOr);
        if (userDTOr.getCode()==505) {
            throw new OAuth2Exception(userDTOr.getMsg());
        }
        UserDto  userDTO= userDTOr.getData();
        if (ObjectUtils.isEmpty(userDTO)) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDTO.setRoles(CollUtil.toList("ADMIN"));
        SecurityUser securityUser = new SecurityUser(userDTO);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

}
