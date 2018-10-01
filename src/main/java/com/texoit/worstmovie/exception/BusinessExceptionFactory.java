package com.texoit.worstmovie.exception;

public class BusinessExceptionFactory {

    private BusinessExceptionFactory() {}

    public static BusinessException buildException(EnumBusinessException businessEnum) {
        switch (businessEnum) {
            case MOVIE_IS_WINNER: return new MovieIsWinnerException();
            case MORE_THAN_ONE_FILE: return new MoreThanOneFileException();
            case NOT_A_CSV: return new NotACSVException();
            case NO_FILE: return new NoFileException();
            default: return new UnknownException();
        }
    }
}
