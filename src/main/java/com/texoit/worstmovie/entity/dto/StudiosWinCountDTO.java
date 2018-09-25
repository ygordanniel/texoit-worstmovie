package com.texoit.worstmovie.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudiosWinCountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<StudioWinCountDTO> studios = new ArrayList<>();

    public List<StudioWinCountDTO> getStudios() {
        return studios;
    }

    public void setStudios(List<StudioWinCountDTO> studios) {
        this.studios = studios;
    }

    public void add(StudioWinCountDTO dto) {
        this.studios.add(dto);
    }
}
