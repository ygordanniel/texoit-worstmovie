package com.texoit.worstmovie.entity.dto;

import java.io.Serializable;

public class StudioWinCountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer winCount;

    public StudioWinCountDTO(String name, Integer winCount) {
        this.name = name;
        this.winCount = winCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }
}
