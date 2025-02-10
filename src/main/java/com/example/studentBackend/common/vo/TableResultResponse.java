package com.example.studentBackend.common.vo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页查询VO，基础继承返回信息
 * @param <T>
 */
@Getter
public class TableResultResponse<T> extends BaseResponse {

    /**
     * -- GETTER --
     *  内部类的构造函数，最终返回数据。
     *
     */
    TableData<T> data;

    public TableResultResponse(long total, List<T> rows, Map<String, Object> params) {
        this.data = new TableData<>(total, rows, params);
    }

    @Getter
    public static class TableData<T> {
        long total;
        List<T> rows;

        public TableData(long total, List<T> rows, Map<String, Object> params) {
            this.total = total;

            if (!params.isEmpty()) {
                int page = Integer.parseInt(params.get("page").toString());
                int limit = Integer.parseInt(params.get("limit").toString());
                int startIndex = (page - 1) * limit;
                int endIndex = Math.min(startIndex + limit, rows.size());
                // 防止 startIndex 超过 rows.size()
                if (startIndex >= rows.size()) {
                    this.rows = new ArrayList<>();
                } else {
                    // 确保 endIndex 不小于 startIndex
                    endIndex = Math.max(endIndex, startIndex);
                    this.rows = new ArrayList<>(rows.subList(startIndex, endIndex));
                }
            } else {
                this.rows = rows;
            }
        }

    }
}
