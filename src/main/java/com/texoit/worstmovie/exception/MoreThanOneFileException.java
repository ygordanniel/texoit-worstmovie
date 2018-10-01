package com.texoit.worstmovie.exception;

public class MoreThanOneFileException extends BusinessException {

    public MoreThanOneFileException() {
        super("more_than_one_csv");
        setExceptionEnum(EnumBusinessException.MORE_THAN_ONE_FILE);
    }
}
