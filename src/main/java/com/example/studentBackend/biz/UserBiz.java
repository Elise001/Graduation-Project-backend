package com.example.studentBackend.biz;


import com.example.studentBackend.common.mybatis.BaseBusinessBiz;
import com.example.studentBackend.entity.User;
import com.example.studentBackend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 * @author Savor
 * @version 2025-01-31 13:32:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBusinessBiz<UserMapper,User> {

    public List<User> pageQuery(Map<String, Object> params) {
        return this.mapper.selectListQuery(params);
    }

}