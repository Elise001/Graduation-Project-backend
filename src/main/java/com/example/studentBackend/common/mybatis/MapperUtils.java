package com.example.studentBackend.common.mybatis;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class MapperUtils {

    /**
     * 批量插入，CommonConstant.DEFAULT_BATCH_SIZE条数据插入一次，字段为null会插入
     *
     * @param mapper entityMapper
     * @param list   list
     */
    public static int insertList(CommonMapper mapper, List list) {
        return insertList(mapper, list, 500);
    }

    /**
     * 批量插入，batchSize条数据插入一次，字段为null会插入
     *
     * @param mapper    entityMapper
     * @param list      list
     * @param batchSize 插入条数
     */
    public static int insertList(CommonMapper mapper, List list, int batchSize) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        List insertList = new ArrayList<>();
        batchSize = getBatchSize(batchSize);
        for (int i = 0; i < list.size(); i++) {
            insertList.add(list.get(i));
            if ((i + 1) % batchSize == 0) {
                mapper.insertList(insertList);
                insertList.clear();
            }
        }
        if (!CollectionUtils.isEmpty(insertList)) {
            mapper.insertList(insertList);
        }
        return list.size();
    }

    private static int getBatchSize(int batchSize) {
        return batchSize <= 0 ? 500 : batchSize;
    }
}
