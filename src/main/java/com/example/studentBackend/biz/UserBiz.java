package com.example.studentBackend.biz;


import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.common.util.JwtUtil;
import com.example.studentBackend.common.util.PasswordUtil;
import com.example.studentBackend.common.vo.BusinessException;
import com.example.studentBackend.common.vo.ObjectRestResponse;
import com.example.studentBackend.entity.User;
import com.example.studentBackend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 * @author Savor
 * @version 2025-01-31 13:32:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBusinessBiz<UserMapper,User> {

    @Resource
    private JwtUtil jwtUtil;

    public List<User> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

    public void add(User entity) {
        User user = new User();
        user.setUsername(entity.getUsername());
        List<User> users = this.selectList(user);
        if (!users.isEmpty()) {
            throw new BusinessException("该账户名称已存在，请尝试直接登录！");
        }

        entity.setPassword(PasswordUtil.encryptPassword(entity.getPassword()));
        entity.setCrtTime(new Date());
        this.insertSelective(entity);
    }

    public ObjectRestResponse<String> login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        User user1 = this.selectList(user)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BusinessException("该登录账号不存在，请校验！"));
        String pwdOld = user1.getPassword();

        if (PasswordUtil.matches(password, pwdOld)) {
            String token = jwtUtil.generateToken(username);
            return new ObjectRestResponse<String>().data(token);
        } else {
            throw new BusinessException("密码错误，请校验！");
        }
    }
}