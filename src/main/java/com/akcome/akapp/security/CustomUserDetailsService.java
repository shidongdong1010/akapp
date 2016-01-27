package com.akcome.akapp.security;

import com.akcome.akapp.common.MessageHelper;
import com.akcome.akapp.domain.User;
import com.akcome.akapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by dongdongshi on 16/1/12.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MessageHelper messageHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用User中的email作为用户名:
        User user = userService.findUserByUsername(username);
        if (user == null) {
            // throw new UsernameNotFoundException(messageHelper.getMessage("AbstractUserDetailsAuthenticationProvider.UsernameNotFound"));
            throw new UsernameNotFoundException("AbstractUserDetailsAuthenticationProvider.UsernameNotFound");
        }
        // SecurityUser实现UserDetails并将SUser的Email映射为username
        return new SecurityUser(user); //code9
    }
}
