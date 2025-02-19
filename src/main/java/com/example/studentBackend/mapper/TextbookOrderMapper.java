package com.example.studentBackend.mapper;

import com.example.studentBackend.common.mybatis.CommonMapper;
import org.springframework.stereotype.Repository;
import com.example.studentBackend.entity.TextbookOrder;
import java.util.List;
import java.util.Map;

/**
 * 教材订单表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@Repository
public interface TextbookOrderMapper extends CommonMapper<TextbookOrder> {

    List<TextbookOrder> textbookReservationQuery(Map<String, Object> params);

    List<TextbookOrder> collectQuery(Map<String, Object> params);

    List<TextbookOrder> refundQuery(Map<String, Object> params);

    List<TextbookOrder> paymentQuery(Map<String, Object> params);
}
