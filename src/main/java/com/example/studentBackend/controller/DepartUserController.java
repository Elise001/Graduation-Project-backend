package com.example.studentBackend.controller;

import com.example.studentBackend.biz.DepartUserBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.DepartUser;
import com.example.studentBackend.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 部门-用户关系表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@RestController
@RequestMapping("departUser")
public class DepartUserController extends BaseController<DepartUserBiz, DepartUser> {

    @GetMapping({"pageQuery"})
    public TableResultResponse pageQuery(@RequestParam Map<String, Object> params) {
        List<DepartUser> departList = this.baseBiz.pageQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }

    @GetMapping({"getDepartUser"})
    public TableResultResponse getDepartUser(@RequestParam Map<String, Object> params) {
        List<User> userList = this.baseBiz.getDepartUser(params);
        return new TableResultResponse<>(userList.size(), userList, params);
    }
}