package com.example.studentBackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * 开启事务支持
 * 配置Mapper扫描注解，交给spring自动进行bean工厂创建
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.studentBackend.mapper")
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
