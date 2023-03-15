package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 君墨笑
 * @date 2023/3/9
 */
@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    /**
     * 未经过登录认证的接口调用会重定向到这里
     */
    @GetMapping("/loginError")
    public String loginError() {
        return "你未登录，请登录";
    }


    @PostMapping("/login")
    public String login(String username, String password) {
        return securityService.login(username, password);
    }


    @PostMapping("/loginOut")
    public String loginOut(String username, String password) {
        return "退出成功";
    }
}
