package com.texoit.worstmovie.exception;

public class NoFileException extends BusinessException {

    public NoFileException() {
        super("no_file");
        setExceptionEnum(BusinessExceptionEnum.NO_FILE);
    }
}
