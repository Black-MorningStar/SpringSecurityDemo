package com.example.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 君墨笑
 * @date 2023/3/9
 */
@Service
public class SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        Authentication authenticate = authenticationManager.authenticate(authRequest);
        System.out.println("登录成功");
        return "登录成功";
    }
}
