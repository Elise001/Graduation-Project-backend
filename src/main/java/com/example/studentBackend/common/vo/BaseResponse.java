package com.example.studentBackend.common.vo;

import lombok.Getter;

/**
 * 基础返回信息
 */
@Getter
public class BaseResponse {
    public static final int SERVER_SUCCESS_STATUS = 200;
    public static final int SERVER_FAILED_STATUS = 500;
    protected int status = 200;
    protected String message;
    protected boolean success = true;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse(int status, String message, boolean isSuccess) {
        this.status = status;
        this.message = message;
        this.success = isSuccess;
    }

    public BaseResponse() {
        //
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

