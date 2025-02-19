package com.example.studentBackend.controller;

import com.example.studentBackend.biz.TextbookOrderBiz;
import com.example.studentBackend.common.mybatis.BaseController;
import com.example.studentBackend.common.util.OrderNumberUtils;
import com.example.studentBackend.common.vo.ObjectRestResponse;
import com.example.studentBackend.common.vo.TableResultResponse;
import com.example.studentBackend.entity.TextbookOrder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private OrderNumberUtils orderNumberUtils;

    @GetMapping({"textbookReservationQuery"})
    public TableResultResponse<TextbookOrder> pageQuery(@RequestParam Map<String, Object> params) {
        List<TextbookOrder> departList = this.baseBiz.textbookReservationQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }

    @GetMapping({"collectQuery"})
    public TableResultResponse<TextbookOrder> collectQuery(@RequestParam Map<String, Object> params) {
        List<TextbookOrder> departList = this.baseBiz.collectQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }

    @GetMapping({"refundQuery"})
    public TableResultResponse<TextbookOrder> refundQuery(@RequestParam Map<String, Object> params) {
        List<TextbookOrder> departList = this.baseBiz.refundQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }

    @GetMapping({"paymentQuery"})
    public TableResultResponse<TextbookOrder> paymentQuery(@RequestParam Map<String, Object> params) {
        List<TextbookOrder> departList = this.baseBiz.paymentQuery(params);
        return new TableResultResponse<>(departList.size(), departList, params);
    }

    @Override
    @PostMapping({""})
    public ObjectRestResponse<TextbookOrder> add(@RequestBody TextbookOrder entity) {
        entity.setOrderCode(orderNumberUtils.generate("JC"));
        this.baseBiz.add(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"batch"})
    public ObjectRestResponse<TextbookOrder> batch(@RequestBody List<TextbookOrder> list) throws InvocationTargetException, IllegalAccessException {
        this.baseBiz.batch(list);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"studentSure"})
    public ObjectRestResponse<TextbookOrder> studentSure(@RequestBody TextbookOrder entity) {
        this.baseBiz.studentSure(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"orderStatus01"})
    public ObjectRestResponse<TextbookOrder> orderStatus01(@RequestBody TextbookOrder entity) {
        this.baseBiz.orderStatus01(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"orderStatus02"})
    public ObjectRestResponse<TextbookOrder> orderStatus02(@RequestBody TextbookOrder entity) {
        this.baseBiz.orderStatus02(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"orderStatus03"})
    public ObjectRestResponse<TextbookOrder> orderStatus03(@RequestBody TextbookOrder entity) {
        this.baseBiz.orderStatus03(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"orderStatus04"})
    public ObjectRestResponse<TextbookOrder> orderStatus04(@RequestBody TextbookOrder entity) {
        this.baseBiz.orderStatus04(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"orderStatus05"})
    public ObjectRestResponse<TextbookOrder> orderStatus05(@RequestBody TextbookOrder entity) {
        this.baseBiz.orderStatus05(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"orderStatus06"})
    public ObjectRestResponse<TextbookOrder> orderStatus06(@RequestBody TextbookOrder entity) {
        this.baseBiz.orderStatus06(entity);
        return new ObjectRestResponse<>();
    }

    @PostMapping({"orderStatus07"})
    public ObjectRestResponse<TextbookOrder> orderStatus07(@RequestBody TextbookOrder entity) {
        this.baseBiz.orderStatus07(entity);
        return new ObjectRestResponse<>();
    }
}