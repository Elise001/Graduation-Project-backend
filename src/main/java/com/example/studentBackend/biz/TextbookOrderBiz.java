package com.example.studentBackend.biz;

import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.common.util.OrderNumberUtils;
import com.example.studentBackend.common.vo.BaseException;
import com.example.studentBackend.entity.TextbookManager;
import com.example.studentBackend.entity.TextbookOrder;
import com.example.studentBackend.mapper.TextbookOrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
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

    public List<TextbookOrder> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

    public void add(TextbookOrder entity) {
        entity.setOrderCode(orderNumberUtils.generate("JC"));
        entity.setOrderStatus("00");
        this.insertSelective(entity);
    }

    public void batch(List<TextbookOrder> list) throws InvocationTargetException, IllegalAccessException {
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
}