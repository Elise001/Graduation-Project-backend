package com.example.studentBackend.biz;

import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.entity.TextbookOperate;
import com.example.studentBackend.mapper.TextbookOperateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 操作日志表
 * @author Savor
 * @version 2025-02-19 22:53:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TextbookOperateBiz extends BaseBusinessBiz<TextbookOperateMapper,TextbookOperate> {

    public List<TextbookOperate> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

}