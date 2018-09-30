package com.texoit.worstmovie.exception;

public class MoreThanOneCSVException extends BusinessException {

    public MoreThanOneCSVException() {
        super("more_than_one_csv");
        setExceptionEnum(BusinessExceptionEnum.MORE_THAN_ONE_CSV);
    }
}
