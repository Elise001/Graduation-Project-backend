package com.example.studentBackend.controller;


import com.example.studentBackend.biz.UserBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.vo.ObjectRestResponse;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 * @author Savor
 * @version 2025-01-31 13:32:17
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz,User> {

    @GetMapping({"pageQuery"})
    public TableResultResponse pageQuery(@RequestParam Map<String, Object> params) {
        List<User> userList = this.baseBiz.pageQuery(params);
        return new TableResultResponse<>(userList.size(), userList, params);
    }

    @PostMapping({"/add"})
    public ObjectRestResponse<User> add(@RequestBody User entity) {
        this.baseBiz.add(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"/jwt/token"})
    public ObjectRestResponse<HashMap> login(@RequestBody User entity) {
        return this.baseBiz.login(entity.getUsername(), entity.getPassword());
    }

    @GetMapping({"/userInfo/{token}"})
    @ResponseBody
    public ObjectRestResponse<User> userInfo(@PathVariable String token) {
        return this.baseBiz.userInfo(token);
    }
}