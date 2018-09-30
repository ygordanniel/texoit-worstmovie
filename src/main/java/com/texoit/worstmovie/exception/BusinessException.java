package com.texoit.worstmovie.exception;

public class BusinessException extends Exception {

    private BusinessExceptionEnum exceptionEnum;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
