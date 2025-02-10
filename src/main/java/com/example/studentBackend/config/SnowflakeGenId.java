package com.example.studentBackend.config;

import com.example.studentBackend.common.util.BeanUtils;
import com.example.studentBackend.common.util.SnowflakeIdWorker;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.genid.GenId;

/**
 * 雪花算法，服务于持久层，不建议在处理业务时调用
 */
@Component
public class SnowflakeGenId implements GenId<Long> {
    public SnowflakeGenId() {
    }

    public Long genId(String table, String column) {
        return BeanUtils.getBean("snowflakeIdWorker", SnowflakeIdWorker.class).nextId();
    }
}
