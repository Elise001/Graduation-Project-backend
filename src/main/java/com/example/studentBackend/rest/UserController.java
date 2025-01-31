package com.example.studentBackend.rest;


import com.example.studentBackend.biz.UserBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}