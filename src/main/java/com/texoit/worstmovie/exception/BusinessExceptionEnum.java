package com.texoit.worstmovie.exception;

public enum BusinessExceptionEnum {
    MOVIE_IS_WINNER("movie_is_winner", "You can't remove a movie who has won a award"),
    NO_FILE("no_file", "You haven't send a file to import."),
    MORE_THAN_ONE_CSV("more_than_one_file", "You have sent more than one file to import. You can only import one CSV " +
            "at a time."),
    NOT_A_CSV("not_a_csv", "The file you have sent isn't a CSV."),
    UNKNOWN_EXCEPTION("unknown_exception", "Ops. Something bad happend and we are not sure what. :(");

    private String code;
    private String message;

    BusinessExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
