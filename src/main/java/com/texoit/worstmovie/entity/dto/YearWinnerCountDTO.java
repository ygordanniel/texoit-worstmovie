package com.texoit.worstmovie.entity.dto;

import java.io.Serializable;

public class YearWinnerCountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer year;
    private Integer winnerCount;

    public YearWinnerCountDTO(Integer year, Integer winnerCount) {
        this.year = year;
        this.winnerCount = winnerCount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWinnerCount() {
        return winnerCount;
    }

    public void setWinnerCount(Integer winnerCount) {
        this.winnerCount = winnerCount;
    }
}
