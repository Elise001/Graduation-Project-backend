package com.example.studentBackend.mapper;

import com.example.studentBackend.common.mybatis.CommonMapper;
import com.example.studentBackend.entity.Depart;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@Repository
public interface DepartMapper extends CommonMapper<Depart> {

    List<Depart> selectListQuery(Map<String, Object> params);
}
