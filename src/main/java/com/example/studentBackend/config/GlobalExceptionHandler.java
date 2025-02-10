package com.example.studentBackend.config;

import com.example.studentBackend.common.vo.BaseException;
import com.example.studentBackend.common.vo.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常配置类，全局处理应用程序中的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({BaseException.class})
    public BaseResponse baseExceptionHandler(BaseException ex) {
        this.logger.info(ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public BaseResponse otherExceptionHandler(Exception ex) {
        this.logger.error(ex.getMessage(), ex);
        return new BaseResponse(500, "服务器内部错误，请联系管理员");
    }
}
