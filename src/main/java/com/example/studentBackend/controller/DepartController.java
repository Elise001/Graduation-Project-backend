package com.example.studentBackend.controller;


import com.example.studentBackend.biz.DepartBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.Depart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 部门表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@RestController
@RequestMapping("depart")
public class DepartController extends BaseController<DepartBiz, Depart> {

    @GetMapping({"pageQuery"})
    public TableResultResponse pageQuery(@RequestParam Map<String, Object> params) {
        List<Depart> departList = this.baseBiz.pageQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }
}