package com.example.studentBackend.biz;

import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.entity.DepartUser;
import com.example.studentBackend.mapper.DepartUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 部门-用户关系表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartUserBiz extends BaseBusinessBiz<DepartUserMapper, DepartUser> {

    public List<DepartUser> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

}