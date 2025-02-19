package com.example.studentBackend.mapper;

import com.example.studentBackend.common.mybatis.CommonMapper;
import org.springframework.stereotype.Repository;
import com.example.studentBackend.entity.TextbookOperate;
import java.util.List;
import java.util.Map;

/**
 * 操作日志表
 * @author Savor
 * @version 2025-02-19 22:53:46
 */
@Repository
public interface TextbookOperateMapper extends CommonMapper<TextbookOperate> {

    List<TextbookOperate> selectListQuery(Map<String, Object> params);
}
