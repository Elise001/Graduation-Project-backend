package com.example.studentBackend.biz;

import com.example.studentBackend.annotation.EnforceProcess;
import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.common.util.OrderNumberUtils;
import com.example.studentBackend.common.vo.BaseException;
import com.example.studentBackend.entity.TextbookManager;
import com.example.studentBackend.entity.TextbookOrder;
import com.example.studentBackend.mapper.TextbookOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 教材订单表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TextbookOrderBiz extends BaseBusinessBiz<TextbookOrderMapper,TextbookOrder> {

    @Resource
    private TextbookManagerBiz textbookManagerBiz;

    @Resource
    private OrderNumberUtils orderNumberUtils;

    public List<TextbookOrder> textbookReservationQuery(Map<String, Object> params) {
        return this.mapper.textbookReservationQuery(params);
    }

    public List<TextbookOrder> collectQuery(Map<String, Object> params) {
        return this.mapper.collectQuery(params);
    }

    public List<TextbookOrder> paymentQuery(Map<String, Object> params) {
        List<TextbookOrder> textbookOrders = this.mapper.paymentQuery(params);
        textbookOrders.forEach(item -> {
            if (StringUtils.isNotBlank(item.getRef1())) {
                item.setRef2(String.valueOf(item.getPrice().multiply(item.getCount().subtract(new BigDecimal(item.getRef1())))));
            } else {
                item.setRef2(String.valueOf(item.getPrice().multiply(item.getCount())));
            }
        });
        return textbookOrders;
    }

    public List<TextbookOrder> refundQuery(Map<String, Object> params) {
        return this.mapper.refundQuery(params);
    }

    @EnforceProcess(orderStatus = "00")
    public void add(TextbookOrder entity) {
        TextbookOrder textbookOrder = new TextbookOrder();
        textbookOrder.setYear(entity.getYear());
        textbookOrder.setMajor(entity.getMajor());
        textbookOrder.setRef3(entity.getRef3());
        textbookOrder.setTextbookCode(entity.getTextbookCode());
        Long l = this.selectCount(textbookOrder);
        if (l != 0) {
            throw new BaseException("表中已存在年级、专业、班级书籍编号相同的数据！");
        }

        entity.setOrderStatus("00");
        this.insertSelective(entity);
    }

    public void batch(List<TextbookOrder> list) {
        // 使用 HashSet 来存储已有的年级+专业+教材编号组合
        Set<String> existingCombinations = this.selectListAll()
                .stream()
                .map(item -> item.getYear() + "-" + item.getMajor() + "-" + item.getTextbookCode())
                .collect(Collectors.toSet());

        List<TextbookOrder> textbookOrders = new ArrayList<>();
        for (TextbookOrder item : list) {
            String combination = item.getYear() + "-" + item.getMajor() + "-" + item.getTextbookCode();
            if (existingCombinations.contains(combination)) {
                throw new BaseException("表中已存在相同的数据！" + combination);
            }
            existingCombinations.add(combination); // 添加到集合中，防止后续插入重复数据

            item.setOrderCode(orderNumberUtils.generate("JC"));
            item.setOrderStatus("00");

            TextbookManager textbookManager = new TextbookManager();
            textbookManager.setTextbookCode(item.getTextbookCode());
            TextbookManager textbookManager1 = textbookManagerBiz.selectOne(textbookManager);
            if (textbookManager1 == null) {
                throw new BaseException("教材编号不存在！" + item.getTextbookCode());
            }

            item.setTextbookName(textbookManager1.getTextbookName());
            item.setPrice(textbookManager1.getPrice());
            textbookOrders.add(item);
        }
        this.insertList(textbookOrders);
    }

    @EnforceProcess(orderStatus = "01")
    public void studentSure(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }

    @EnforceProcess(orderStatus = "02")
    public void orderStatus01(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }

    @EnforceProcess(orderStatus = "03")
    public void orderStatus02(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }

    @EnforceProcess(orderStatus = "04")
    public void orderStatus03(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }

    @EnforceProcess(orderStatus = "05")
    public void orderStatus04(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }

    @EnforceProcess(orderStatus = "06")
    public void orderStatus05(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }

    @EnforceProcess(orderStatus = "07")
    public void orderStatus06(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }

    @EnforceProcess(orderStatus = "08")
    public void orderStatus07(TextbookOrder entity) {
        this.updateSelectiveById(entity);
    }
}