package com.texoit.worstmovie.exception;

public class MovieIsWinnerException extends BusinessException {

    public MovieIsWinnerException() {
        super("movie_is_winner");
        setExceptionEnum(BusinessExceptionEnum.MOVIE_IS_WINNER);
    }
}
