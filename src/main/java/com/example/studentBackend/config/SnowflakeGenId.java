package com.example.studentBackend.config;

import com.example.studentBackend.common.util.BeanUtils;
import com.example.studentBackend.common.util.SnowflakeIdWorker;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.genid.GenId;

/**
 * 服务于持久层
 */
@Component
public class SnowflakeGenId implements GenId<Long> {
    public SnowflakeGenId() {
    }

    public Long genId(String table, String column) {
        return BeanUtils.getBean("snowflakeIdWorker", SnowflakeIdWorker.class).nextId();
    }
}
