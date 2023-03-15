package com.example.springsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author 君墨笑
 * @date 2023/3/9
 */

public class MyUserDetailsService implements UserDetailsService  {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contentEquals("psx")) {
            return new MyUserDetails(username,"$2a$10$cRVcr5ZOUhnmF3lcTRZMdu3BFT/US9O8gOuGW98wFWw3swk0cWwd2");
        }
        return null;
    }
}
