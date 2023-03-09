package com.example.springsecuritydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

/**
 * @author 君墨笑
 * @date 2023/3/6
 */
@RestController
public class DemoController {

    @PostMapping("/springsecurity/test1")
    public String test1() {
        return "测试回复";
    }


    @PostMapping("/springsecurity/login")
    public String login(String username, String password) {
        System.out.println("用户名：" + username + " 密码：" + password);
        return "登录成功";
    }

    @GetMapping("/springsecurity/loginError")
    public String loginError() {
        return "你还未登录，请先登录";
    }
}
