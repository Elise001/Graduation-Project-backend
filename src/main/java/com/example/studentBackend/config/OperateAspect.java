package com.example.studentBackend.config;

import com.example.studentBackend.annotation.EnforceProcess;
import com.example.studentBackend.biz.TextbookOperateBiz;
import com.example.studentBackend.entity.TextbookOperate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * AOP的实现，通过切点Aspect
 */
@Slf4j
@Aspect
@Component
public class OperateAspect {

    @Resource
    private TextbookOperateBiz textbookOperateBiz;

    @After("@annotation(enforceProcess)")
    @SuppressWarnings({"unused"})
    public void testCut(JoinPoint joinPoint, EnforceProcess enforceProcess) {
        // 获取自定义参数的值
        String orderStatus = enforceProcess.orderStatus();

        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];

        // 获取未知实体类的属性
        Field[] fields = entity.getClass().getDeclaredFields();
        Map<String, Object> entityMap = new HashMap<>();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(entity);
                entityMap.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                // @Data，忽略无法访问的属性
            }
        }

        this.insertOperate(entityMap, orderStatus);
    }

    @SuppressWarnings({"unused"})
    private void insertOperate(Map<String, Object> entityMap, String orderStatus) {
        TextbookOperate textbookOperate = new TextbookOperate();
        textbookOperate.setOrderCode(entityMap.getOrDefault("orderCode", "null").toString());
        textbookOperate.setTextbookCode(entityMap.getOrDefault("textbookCode", "null").toString());
        textbookOperate.setCrtTime(new Date());
        Object value = entityMap.get("ref2");
        if (value instanceof String) {
            textbookOperate.setOrderAmount(new BigDecimal(String.valueOf(value)));
        } else {
            // 处理非BigDecimal或空值情况
            textbookOperate.setOrderAmount(null); // 示例默认值
        }

        String operate = null;
        String crtUserName = entityMap.getOrDefault("crtUserName", "null").toString();
        switch (orderStatus) {
            case "00":
                operate=crtUserName + "：发起了教材预订";
                break;
            case "01":
                operate=crtUserName + "：确认教材预订信息无误";
                break;
            case "02":
                operate=crtUserName + "：申请打印领书单";
                break;
            case "03":
                operate=crtUserName + "：教材科审核打印通过";
                break;
            case "04":
                operate=crtUserName + "：进行了领取图书操作";
                break;
            case "05":
                operate=crtUserName + "：申请退换货图书";
                break;
            case "06":
                operate=crtUserName + "：教材科审核退换通过";
                break;
            case "07":
                operate=crtUserName + "：仓库管理确认";
                break;
            case "08":
                operate=crtUserName + "：学生支付完成，" + "支付金额：" + textbookOperate.getOrderAmount();
                break;
        }
        textbookOperate.setOperate(operate);
        this.textbookOperateBiz.insertSelective(textbookOperate);
    }
}
