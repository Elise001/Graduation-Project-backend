package com.example.studentBackend.biz;

import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.entity.TextbookOrder;
import com.example.studentBackend.mapper.TextbookOrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 教材订单表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TextbookOrderBiz extends BaseBusinessBiz<TextbookOrderMapper,TextbookOrder> {

    public List<TextbookOrder> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

}