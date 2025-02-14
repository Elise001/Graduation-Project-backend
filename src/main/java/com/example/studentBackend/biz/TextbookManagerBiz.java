package com.example.studentBackend.biz;

import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.common.vo.BaseException;
import com.example.studentBackend.entity.TextbookManager;
import com.example.studentBackend.mapper.TextbookManagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 教材管理表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TextbookManagerBiz extends BaseBusinessBiz<TextbookManagerMapper,TextbookManager> {

    public List<TextbookManager> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

    public void add(List<TextbookManager> list) {
        // 使用 HashSet 来存储已有的年级+专业+教材编号组合
        Set<String> existingCombinations = this.selectListAll()
                .stream()
                .map(item -> item.getYear() + "-" + item.getMajor() + "-" + item.getTextbookCode())
                .collect(Collectors.toSet());

        for (TextbookManager item : list) {
            String combination = item.getYear() + "-" + item.getMajor() + "-" + item.getTextbookCode();
            if (existingCombinations.contains(combination)) {
                throw new BaseException("表中已存在相同的数据！" + combination);
            }
            existingCombinations.add(combination); // 添加到集合中，防止后续插入重复数据
        }

        this.mapper.insertList(list);
    }
}