package com.example.springsecuritydemo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springsecuritydemo.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义过滤器用来认证
 *
 * @author pengshaoxiang
 */
public class MyAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //此处解析jwt获取用户信息
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            //解析用户信息放入
            Map<String, String> user = JWTUtils.analyzeToken(token);
            if (user != null) {
                String userId = user.get("userId");
                String permission = user.get("permission");
                List<String> permissionList = JSONObject.parseArray(permission, String.class);
                List<SimpleGrantedAuthority> authorityList = permissionList.stream().map(it -> new SimpleGrantedAuthority(it)).collect(Collectors.toList());
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(userId, null, authorityList);
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
