package com.texoit.worstmovie.exception;

public class BusinessExceptionFactory {

    private BusinessExceptionFactory() {}

    public static BusinessException buildException(BusinessExceptionEnum businessEnum) {
        switch (businessEnum) {
            case MOVIE_IS_WINNER: return new MovieIsWinnerException();
            case MORE_THAN_ONE_CSV: return new MoreThanOneCSVException();
            case NOT_A_CSV: return new NotACSVException();
            case NO_FILE: return new NoFileException();
            default: return new UnknownException();
        }
    }
}
