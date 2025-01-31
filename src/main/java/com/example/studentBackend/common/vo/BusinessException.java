package com.example.studentBackend.common.vo;

public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message, 30101);
    }

    public BusinessException(String message, int code) {
        super(message, code);
    }
}

