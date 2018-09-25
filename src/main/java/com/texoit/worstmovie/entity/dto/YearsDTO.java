package com.texoit.worstmovie.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class YearsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<YearWinnerCountDTO> years = new ArrayList<>();

    public List<YearWinnerCountDTO> getYears() {
        return years;
    }

    public void setYears(List<YearWinnerCountDTO> years) {
        this.years = years;
    }

    public void add(YearWinnerCountDTO dto) {
        this.years.add(dto);
    }
}
