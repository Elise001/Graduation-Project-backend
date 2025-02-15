package com.example.studentBackend.common.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrderNumberUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private static final AtomicInteger sequence = new AtomicInteger(1);
    private static String lastDate = dateFormat.format(new Date());

    public synchronized String generate(String prefix) {
        String currentDate = dateFormat.format(new Date());

        // 如果日期变化，重置流水号
        if (!currentDate.equals(lastDate)) {
            sequence.set(1);
            lastDate = currentDate;
        }

        // 获取当前日期并格式化为8位时间戳

        // 获取当前流水号并格式化为三位
        String seq = String.format("%03d", sequence.getAndIncrement());

        // 组合业务前缀、时间戳和流水号
        return prefix + currentDate + seq;
    }

    public static void main(String[] args) {
        OrderNumberUtils orderService = new OrderNumberUtils();
        System.out.println(orderService.generate("DB"));
        System.out.println(orderService.generate("DB"));
    }
}
