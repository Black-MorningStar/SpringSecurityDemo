package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.MyResponse;
import com.example.springsecuritydemo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public MyResponse loginError() {
        return MyResponse.fail("你未登录，请登录");
    }


    @PostMapping("/login")
    public MyResponse login(String username, String password) {
        String token = securityService.login(username, password);
        return MyResponse.success(token);
    }

    @PostMapping("/loginOut")
    public String loginOut(String username, String password) {
        return "退出成功";
    }

    @PostMapping("/busienss")
    public MyResponse busienss() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> user = (Map<String, String>) authentication.getPrincipal();
        String message = "登录用户ID为: " + user.get("userId") + " 用户名为:" + user.get("userName");
        return MyResponse.success(message);
    }
}
