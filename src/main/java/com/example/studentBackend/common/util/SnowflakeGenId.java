package com.example.studentBackend.common.util;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.genid.GenId;

@Component
public class SnowflakeGenId implements GenId<Long> {
    public SnowflakeGenId() {
    }

    public Long genId(String table, String column) {
        return BeanUtils.getBean("snowflakeIdWorker", SnowflakeIdWorker.class).nextId();
    }
}
