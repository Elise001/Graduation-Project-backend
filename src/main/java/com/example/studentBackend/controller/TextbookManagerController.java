package com.example.studentBackend.controller;

import com.example.studentBackend.biz.TextbookManagerBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.vo.ObjectRestResponse;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.TextbookManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 教材管理表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@RestController
@RequestMapping("textbookManager")
public class TextbookManagerController extends BaseController<TextbookManagerBiz,TextbookManager> {

    @GetMapping({"pageQuery"})
    public TableResultResponse<TextbookManager> pageQuery(@RequestParam Map<String, Object> params) {
        List<TextbookManager> departList = this.baseBiz.pageQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }

    @PostMapping({"add"})
    public ObjectRestResponse<TextbookManager> add(@RequestBody List<TextbookManager> list) {
        this.baseBiz.add(list);
        return new ObjectRestResponse<>();
    }
}