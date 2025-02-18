package com.example.studentBackend.mapper;

import com.example.studentBackend.common.mybatis.CommonMapper;
import com.example.studentBackend.entity.DepartUser;
import com.example.studentBackend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门-用户关系表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@Repository
public interface DepartUserMapper extends CommonMapper<DepartUser> {

    List<DepartUser> selectListQuery(Map<String, Object> params);


    List<User> getDepartUser(Map<String, Object> params);
}
