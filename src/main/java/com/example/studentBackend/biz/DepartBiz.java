package com.example.studentBackend.biz;

import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.entity.Depart;
import com.example.studentBackend.mapper.DepartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 部门表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartBiz extends BaseBusinessBiz<DepartMapper,Depart> {

    public List<Depart> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }
}