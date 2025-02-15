package com.example.studentBackend.controller;

import com.example.studentBackend.biz.TextbookOrderBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.vo.ObjectRestResponse;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.TextbookOrder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 教材订单表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@RestController
@RequestMapping("textbookOrder")
public class TextbookOrderController extends BaseController<TextbookOrderBiz,TextbookOrder> {

    @GetMapping({"pageQuery"})
    public TableResultResponse<TextbookOrder> pageQuery(@RequestParam Map<String, Object> params) {
        List<TextbookOrder> departList = this.baseBiz.pageQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }

    @Override
    @PostMapping({""})
    public ObjectRestResponse<TextbookOrder> add(@RequestBody TextbookOrder entity) {
        this.baseBiz.add(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"batch"})
    public ObjectRestResponse<TextbookOrder> batch(@RequestBody List<TextbookOrder> list) throws InvocationTargetException, IllegalAccessException {
        this.baseBiz.batch(list);
        return new ObjectRestResponse<>();
    }
}