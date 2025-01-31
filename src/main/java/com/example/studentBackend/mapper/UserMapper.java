package com.example.studentBackend.mapper;

import com.example.studentBackend.common.mybatis.CommonMapper;
import com.example.studentBackend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 * @author Savor
 * @version 2025-01-31 13:32:17
 */
@Repository
public interface UserMapper extends CommonMapper<User> {

    List<User> selectListQuery(Map<String, Object> params);
}
