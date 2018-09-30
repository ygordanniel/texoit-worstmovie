package com.texoit.worstmovie.exception;

public class UnknownException extends BusinessException {

    public UnknownException() {
        super("unknown_exception");
        setExceptionEnum(BusinessExceptionEnum.UNKNOWN_EXCEPTION);
    }
}
