package com.texoit.worstmovie.exception;

public class NotACSVException extends BusinessException {

    public NotACSVException() {
        super("not_a_csv");
        setExceptionEnum(EnumBusinessException.NOT_A_CSV);
    }
}
