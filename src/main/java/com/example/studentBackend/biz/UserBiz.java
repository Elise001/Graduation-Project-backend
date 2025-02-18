package com.example.studentBackend.biz;


import com.example.studentBackend.common.jwt.JwtUtil;
import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.common.util.PasswordUtil;
import com.example.studentBackend.common.vo.BaseException;
import com.example.studentBackend.common.vo.ObjectRestResponse;
import com.example.studentBackend.entity.Depart;
import com.example.studentBackend.entity.DepartUser;
import com.example.studentBackend.entity.User;
import com.example.studentBackend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private DepartUserBiz departUserBiz;

    @Resource
    private DepartBiz departBiz;

    public List<User> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

    public void add(User entity) {
        User user = new User();
        user.setUsername(entity.getUsername());
        List<User> users = this.selectList(user);
        if (!users.isEmpty()) {
            throw new BaseException("该账户名称已存在，请尝试直接登录！");
        }

        entity.setPassword(PasswordUtil.encryptPassword(entity.getPassword()));
        entity.setCrtTime(new Date());
        this.insertSelective(entity);
    }

    public ObjectRestResponse<HashMap> login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setIsDisabled("0");
        User user1 = this.selectList(user)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BaseException("该登录账号不存在或被冻结，请校验！"));
        String pwdOld = user1.getPassword();

        if (PasswordUtil.matches(password, pwdOld)) {
            String token = jwtUtil.generateToken(username);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("token", "bearer " + token);
            return new ObjectRestResponse<HashMap>().data(hashMap);
        } else {
            throw new BaseException("密码错误，请校验！");
        }
    }

    public ObjectRestResponse<User> userInfo(String token) {
        if (token == null || !token.startsWith("bearer ")) {
            throw new BaseException("请求参数异常，请重新登录！");
        }

        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);

        if (username == null) {
            throw new BaseException("错误的token信息~~~~~~~~");
        }

        User user = new User();
        user.setUsername(username);

        User user1 = this.selectOne(user);

        if (user1 == null) {
            throw new BaseException("该账号已被删除，请联系管理员！");
        }

        // 添加权限信息
        DepartUser departUser = new DepartUser();
        departUser.setUserId(user1.getId());
        List<Long> collect = departUserBiz.selectList(departUser).stream().map(DepartUser::getDepartId).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Example build = Example.builder(Depart.class).where(Sqls.custom().andIn("id", collect)).build();
            List<String> collect1 = departBiz.selectByExample(build).stream().map(Depart::getDepartCode).collect(Collectors.toList());
            user1.setDepartCode(collect1);
        }

        return new ObjectRestResponse<User>().data(user1);
    }

    public ObjectRestResponse<String> passwordReset(Long id) {
        User user = new User();
        user.setId(id);
        user.setPassword(PasswordUtil.encryptPassword("123456"));
        this.updateSelectiveById(user);

        return new ObjectRestResponse<String>().data("重置成功");
    }

    public ObjectRestResponse<String> logout(String token) {
        // 这里服务器端的逻辑应该是从redis中清除token，暂不实现
        System.out.println("token清除"+ token);

        return new ObjectRestResponse<>();
    }
}