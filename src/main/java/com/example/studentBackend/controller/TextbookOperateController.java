package com.example.studentBackend.controller;

import com.example.studentBackend.biz.TextbookOperateBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.TextbookOperate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 操作日志表
 * @author Savor
 * @version 2025-02-19 22:53:46
 */
@RestController
@RequestMapping("textbookOperate")
public class TextbookOperateController extends BaseController<TextbookOperateBiz,TextbookOperate> {

    @GetMapping({"pageQuery"})
    public TableResultResponse<TextbookOperate> pageQuery(@RequestParam Map<String, Object> params) {
        List<TextbookOperate> departList = this.baseBiz.pageQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }
}