package com.texoit.worstmovie.exception;

public class BusinessException extends Exception {

    private EnumBusinessException exceptionEnum;

    public BusinessException(String message) {
        super(message);
    }

    public EnumBusinessException getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(EnumBusinessException exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
