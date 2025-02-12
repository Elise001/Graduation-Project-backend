package com.example.studentBackend.common;


import com.example.studentBackend.biz.UserBiz;
import com.example.studentBackend.common.vo.BaseException;
import com.example.studentBackend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserBiz userBiz;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        User user1 = this.userBiz.selectOne(user);
        if (user1 == null) {
            throw new BaseException("恶意篡改登录用户信息，请退出重试！");
        }

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user1.getYear()));

        return new org.springframework.security.core.userdetails.User(
                user1.getUsername(),
                user1.getPassword(),
                authorities
        );
    }
}
