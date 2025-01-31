package com.example.studentBackend.common;


import com.example.studentBackend.biz.UserBiz;
import com.example.studentBackend.common.vo.BusinessException;
import com.example.studentBackend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserBiz userBiz;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        User user1 = this.userBiz.selectList(user)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BusinessException("该登录账号不存在，请校验！"));
        return new org.springframework.security.core.userdetails.User(
                user1.getUsername(),
                user1.getPassword(),
                null
        );
    }
}
