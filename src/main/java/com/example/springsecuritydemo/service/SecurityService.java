package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 君墨笑
 * @date 2023/3/9
 */
@Service
public class SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(String username, String password) {
        try {
            //开始认证
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                    password);
            Authentication authenticate = authenticationManager.authenticate(authRequest);
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            //生成JWT
            List<GrantedAuthority> authorities = (List) userDetails.getAuthorities();
            List<String> permission = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            String token = JWTUtils.createToken(userDetails.getUsername(), permission);
            return token;
        } catch (AuthenticationException x) {
            throw x;
        } catch (Exception e) {
            throw new AuthenticationServiceException("登录失败");
        }
    }
}
