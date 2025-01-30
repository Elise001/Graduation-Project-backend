package com.example.studentBackend.common.vo;

import lombok.Getter;

import java.util.MissingFormatArgumentException;

/**
 * 网络请求VO封装
 * @param <T>
 */
@Getter
public class ObjectRestResponse<T> extends BaseResponse {
    private T data;
    private String message;
    private int status;

    public ObjectRestResponse() {
    }

    public static <T> ObjectRestResponse<T> success() {
        return new ObjectRestResponse<>();
    }

    public static <T> ObjectRestResponse<T> success(T data) {
        return (new ObjectRestResponse<T>()).data(data);
    }

    public static <T> ObjectRestResponse<T> failed() {
        return (new ObjectRestResponse<T>()).status(500);
    }

    public static <T> ObjectRestResponse<T> failed(String message) {
        ObjectRestResponse<T> response = new ObjectRestResponse<>();
        return response.status(500).message(message);
    }

    public ObjectRestResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public ObjectRestResponse<T> message(String message, Object... objs) {
        try {
            this.message = String.format(message.replaceAll("\\{\\}", "%s"), objs);
            return this;
        } catch (MissingFormatArgumentException var4) {
            throw new RuntimeException("设置message失败，占位符与参数个数不匹配: " + message, var4);
        }
    }

    public ObjectRestResponse<T> status(int status) {
        this.status = status;
        return this;
    }

    public ObjectRestResponse<T> data(T data) {
        this.setData(data);
        return this;
    }

    public void setData(T data) {
        this.data = data;
    }
}

