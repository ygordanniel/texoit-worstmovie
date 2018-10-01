package com.texoit.worstmovie.exception;

public class MovieIsWinnerException extends BusinessException {

    public MovieIsWinnerException() {
        super("movie_is_winner");
        setExceptionEnum(EnumBusinessException.MOVIE_IS_WINNER);
    }
}
