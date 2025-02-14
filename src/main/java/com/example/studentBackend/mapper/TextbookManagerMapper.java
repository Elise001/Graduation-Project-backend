package com.example.studentBackend.mapper;

import com.example.studentBackend.common.mybatis.CommonMapper;
import org.springframework.stereotype.Repository;
import com.example.studentBackend.entity.TextbookManager;
import java.util.List;
import java.util.Map;

/**
 * 教材管理表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@Repository
public interface TextbookManagerMapper extends CommonMapper<TextbookManager> {

    List<TextbookManager> selectListQuery(Map<String, Object> params);
}
